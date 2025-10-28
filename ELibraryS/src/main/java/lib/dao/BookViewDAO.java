package lib.dao;

import java.util.List;

import lib.model.Book;

public interface BookViewDAO {
	public List<Book> getNewBook();
	public List<Book> getAllBook();
	public List<String> getAllAuthors();
	public List<Book> getBooksByAuthor(String authorName);
	public Book getBookById(int id) ;
}
