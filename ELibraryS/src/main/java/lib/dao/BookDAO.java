package lib.dao;

import java.sql.*;
import java.util.*;
import lib.model.Book;

public class BookDAO {
    private Connection conn;

    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    // ✅ ADD new book
    public boolean addBook(Book book) {
        String sql = "INSERT INTO books(title, author, category, description, file_path, book_image, added_by) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getDescription());
            ps.setString(5, book.getFile_path());
            ps.setString(6, book.getBook_image());
            ps.setString(7, book.getAddedby());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { 
            e.printStackTrace();
            return false;
        }
    }

    // ✅ LIST all books
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY added_date DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt(" book_id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setCategory(rs.getString("category"));
                b.setDescription(rs.getString("description"));
                b.setFile_path(rs.getString("file_path"));
                b.setBook_image(rs.getString("book_image"));
                b.setAddedby(rs.getString("added_by"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ GET book by ID
    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE book_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book b = new Book();
                    b.setId(rs.getInt("book_id"));
                    b.setTitle(rs.getString("title"));
                    b.setAuthor(rs.getString("author"));
                    b.setCategory(rs.getString("category"));
                    b.setDescription(rs.getString("description"));
                    b.setFile_path(rs.getString("file_path"));
                    b.setBook_image(rs.getString("book_image"));
                    b.setAddedby(rs.getString("added_by"));
                    return b;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ UPDATE book
    public boolean updateBook(Book book) {
        String sql = "UPDATE books SET title=?, author=?, category=?, description=?, file_path=?, book_image=? WHERE book_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getDescription());
            ps.setString(5, book.getFile_path());
            ps.setString(6, book.getBook_image());
            ps.setInt(7, book.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ DELETE book
    public boolean deleteBook(int id) {
        String sql = "DELETE FROM books WHERE book_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

