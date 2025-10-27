package lib.controler;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import lib.DB.DBConnect;
import lib.dao.BookDAO;
import lib.model.Book;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

@WebServlet("/admin")
public class BookServlet extends HttpServlet {

    private BookDAO dao;

    public void init() {
        dao = new BookDAO(DBConnect.getConnection());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            switch (action == null ? "list" : action) {
                case "showAddForm":
                    req.getRequestDispatcher("addBookForm.jsp").forward(req, resp);
                    break;
                case "showUpdateForm":
                    showUpdateForm(req, resp);
                    break;
                case "list":
                default:
                    listBooks(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Check if this is a multipart (add/update) or regular (delete) request
        if (ServletFileUpload.isMultipartContent(req)) {
            handleMultipartPost(req, resp); // Handle add/update
        } else {
            handleRegularPost(req, resp); // Handle delete
        }
    }

    private void handleRegularPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // This handles the non-multipart "delete" form
        try {
            String action = req.getParameter("action");
            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.deleteBook(id);
                resp.sendRedirect("admin?action=list");
            } else {
                // Handle other non-multipart POST actions if any
                resp.sendRedirect("admin?action=list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500, "Error processing request: " + e.getMessage());
        }
    }

    private void handleMultipartPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // This handles the multipart "add" and "update" forms
        String action = null;
        Book book = new Book();
        File uploadDir = new File(getServletContext().getRealPath("/uploads"));
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Store paths. Initialize with existing paths for "update"
        String pdfPath = null;
        String imagePath = null;

        try {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> formItems = upload.parseRequest(req);

            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    // Process regular form fields
                    String fieldName = item.getFieldName();
                    String value = item.getString("UTF-8"); // Use UTF-8 for consistency
                    switch (fieldName) {
                        case "action":
                            action = value;
                            break;
                        case "book_id": // Corrected from "id" to match your update form
                            if (value != null && !value.isEmpty()) {
                                book.setId(Integer.parseInt(value));
                            }
                            break;
                        case "title":
                            book.setTitle(value);
                            break;
                        case "author":
                            book.setAuthor(value);
                            break;
                        case "category":
                            book.setCategory(value);
                            break;
                        case "description":
                            book.setDescription(value);
                            break;
                        case "existingPdf": // Get default PDF path
                            if (pdfPath == null) pdfPath = value;
                            break;
                        case "existingImage": // Get default Image path
                            if (imagePath == null) imagePath = value;
                            break;
                    }
                } else {
                    // Process file uploads
                    if (item.getName() != null && !item.getName().isEmpty()) {
                        String fileName = new File(item.getName()).getName();
                        File storeFile = new File(uploadDir, fileName);

                        // Handle duplicate filenames (your logic was good)
                        int count = 1;
                        while (storeFile.exists()) {
                            String name = fileName.substring(0, fileName.lastIndexOf('.'));
                            String ext = fileName.substring(fileName.lastIndexOf('.'));
                            storeFile = new File(uploadDir, name + "_" + count + ext);
                            count++;
                        }

                        // Write file to disk
                        item.write(storeFile);
                        String savedPath = "uploads/" + storeFile.getName();

                        // --- THIS IS THE KEY FIX ---
                        // Check which file field this belongs to
                        if ("pdfFile".equals(item.getFieldName())) {
                            pdfPath = savedPath; // Set PDF path
                        } else if ("imageFile".equals(item.getFieldName())) {
                            imagePath = savedPath; // Set Image path
                        }
                    }
                }
            }

            // --- After loop, set the final paths on the book object ---
            book.setFile_path(pdfPath);
            book.setBook_image(imagePath);

            // Execute the database action
            if ("add".equals(action)) {
                dao.addBook(book);
            } else if ("update".equals(action)) {
                dao.updateBook(book);
            }

            resp.sendRedirect("admin?action=list");

        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendError(500, "Error uploading file: " + ex.getMessage());
        }
    }

    private void listBooks(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Book> bookList = dao.getAllBooks();
        req.setAttribute("bookList", bookList);
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }

    private void showUpdateForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book existingBook = dao.getBookById(id);
        req.setAttribute("book", existingBook);
        req.getRequestDispatcher("updateBookForm.jsp").forward(req, resp);
    }
}

