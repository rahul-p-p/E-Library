package lib.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import lib.model.Book;

/**
 * This class acts as a Data Access Object.
 * It simulates a database using an in-memory List.
 */
public class BookDAO {
    private static final List<Book> books = new ArrayList<>();
    // Used to generate unique IDs for books.
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    // Static block to initialize with some dummy data.
    static {
        books.add(new Book(idCounter.incrementAndGet(), "The Great Gatsby", "F. Scott Fitzgerald", "A novel about the American dream.", "great-gatsby.pdf"));
        books.add(new Book(idCounter.incrementAndGet(), "To Kill a Mockingbird", "Harper Lee", "A story of racial injustice and loss of innocence.", "mockingbird.pdf"));
        books.add(new Book(idCounter.incrementAndGet(), "1984", "George Orwell", "A dystopian novel about totalitarianism.", "1984.pdf"));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books); // Return a copy to prevent external modification
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addBook(Book book) {
        book.setId(idCounter.incrementAndGet());
        books.add(book);
    }

    public void updateBook(Book updatedBook) {
        books.replaceAll(book -> book.getId() == updatedBook.getId() ? updatedBook : book);
    }

    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }
}

