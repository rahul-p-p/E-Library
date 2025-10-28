package lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lib.model.Book;

public class BookViewImp implements BookViewDAO {
	private Connection conn;

    public BookViewImp(Connection conn) {
        this.conn = conn;
    }

	@Override
	public List<Book> getNewBook() {
		List<Book> list = new ArrayList<Book>();
		Book b= null;
		try {
			String sql = "SELECT * FROM books ORDER BY added_date DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        int i=1;
	        while (rs.next() && i<=5) {
	                b = new Book();
	                b.setId(rs.getInt("book_id"));
	                b.setTitle(rs.getString("title"));
	                b.setAuthor(rs.getString("author"));
	                b.setCategory(rs.getString("category"));
	                b.setDescription(rs.getString("description"));
	                b.setFile_path(rs.getString("file_path"));
	                b.setBook_image(rs.getString("book_image"));
	                b.setAddedby(rs.getString("added_by"));
	                list.add(b);
	                i++;
	            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Book> getAllBook() {
		List<Book> list = new ArrayList<Book>();
		Book b= null;
		try {
			String sql = "SELECT * FROM books ORDER BY book_id";
			PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	                b = new Book();
	                b.setId(rs.getInt("book_id"));
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
	public List<String> getAllAuthors() {
	    List<String> authors = new ArrayList<>();
	    try {
	        String sql = "SELECT DISTINCT author FROM books ORDER BY author ASC";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            authors.add(rs.getString("author"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return authors;
	}

	public List<Book> getBooksByAuthor(String authorName) {
	    List<Book> list = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM books WHERE author = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, authorName);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Book b = new Book();
	            b.setId(rs.getInt("book_id"));
	            b.setTitle(rs.getString("title"));
	            b.setAuthor(rs.getString("author"));
	            b.setCategory(rs.getString("category"));
	            b.setDescription(rs.getString("description"));
	            b.setBook_image(rs.getString("book_image"));
	            b.setFile_path(rs.getString("file_path"));
	            list.add(b);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE book_id=?";
        Book b = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    b = new Book();
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
        return b;
    }
   
}