package lib.controler;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import lib.dao.BookDAO;
import lib.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/admin")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    // IMPORTANT: Configure this path to a directory on your server where uploads should be stored.
    private static final String UPLOAD_DIRECTORY = "uploads"; 
    private static final Logger LOGGER = Logger.getLogger(BookServlet.class.getName());

    @Override
    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "showAddForm":
                request.getRequestDispatcher("addBookForm.jsp").forward(request, response);
                break;
            case "showUpdateForm":
                showUpdateForm(request, response);
                break;
            case "list":
            default:
                listBooks(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getActionFromMultipart(request); // Custom method to get action from multipart form

        switch (action) {
            case "add":
                addBook(request, response);
                break;
            case "update":
                updateBook(request, response);
                break;
            case "delete":
                deleteBook(request, response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }
    
    private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookList", bookDAO.getAllBooks());
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.getBookById(id);
        request.setAttribute("book", existingBook);
        request.getRequestDispatcher("updateBookForm.jsp").forward(request, response);
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // We get the ID from the form submission, not multipart
        int id = Integer.parseInt(request.getParameter("id"));
        bookDAO.deleteBook(id);
        // Note: You might also want to delete the associated PDF file from the server here.
        response.sendRedirect(request.getContextPath() + "/admin");
    }

    // Since action is part of a multipart form, we can't get it with getParameter directly
    private String getActionFromMultipart(HttpServletRequest request) {
        if (!ServletFileUpload.isMultipartContent(request)) {
            return request.getParameter("action");
        }
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField() && "action".equals(item.getFieldName())) {
                    return item.getString();
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error parsing multipart request for action", e);
        }
        return "list"; // default action
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Book newBook = new Book();
        String fileName = null;

        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form fields
                    switch (item.getFieldName()) {
                        case "title":
                            newBook.setTitle(item.getString());
                            break;
                        case "author":
                            newBook.setAuthor(item.getString());
                            break;
                        case "description":
                            newBook.setDescription(item.getString());
                            break;
                    }
                } else {
                    // Process file upload
                    fileName = new File(item.getName()).getName();
                    if(fileName != null && !fileName.isEmpty()){
                        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) uploadDir.mkdir();
                        item.write(new File(uploadPath + File.separator + fileName));
                        newBook.setFileName(fileName);
                    }
                }
            }
            bookDAO.addBook(newBook);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error adding book", ex);
        }
        response.sendRedirect(request.getContextPath() + "/admin");
    }
    
    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Book bookToUpdate = new Book();
        String newFileName = null;

        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "id":
                            bookToUpdate.setId(Integer.parseInt(item.getString()));
                            break;
                        case "title":
                            bookToUpdate.setTitle(item.getString());
                            break;
                        case "author":
                            bookToUpdate.setAuthor(item.getString());
                            break;
                        case "description":
                            bookToUpdate.setDescription(item.getString());
                            break;
                         case "existingFileName":
                            bookToUpdate.setFileName(item.getString()); // Keep old filename by default
                            break;
                    }
                } else {
                    newFileName = new File(item.getName()).getName();
                    if(newFileName != null && !newFileName.isEmpty()){
                        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) uploadDir.mkdir();
                        item.write(new File(uploadPath + File.separator + newFileName));
                        bookToUpdate.setFileName(newFileName); // Set to new filename
                    }
                }
            }
            bookDAO.updateBook(bookToUpdate);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error updating book", ex);
        }
        response.sendRedirect(request.getContextPath() + "/admin");
    }
}

