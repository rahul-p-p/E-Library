package lib.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String description;
    private String fileName;

    public Book() {
    }

    public Book(int id, String title, String author, String description, String fileName) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.fileName = fileName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
