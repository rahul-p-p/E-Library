package lib.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String description;
    private  String category; 
    private String file_path ;
    private  String book_image;
    private String addedby;
    
    
    public Book() {
    }

    
	public Book(int id, String title, String author, String description, String category, String file_path,
			String book_image, String addedby) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.category = category;
		this.file_path = file_path;
		this.book_image = book_image;
		this.addedby = addedby;
	}


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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getBook_image() {
		return book_image;
	}

	public void setBook_image(String book_image) {
		this.book_image = book_image;
	}

	public String getAddedby() {
		return addedby;
	}

	public void setAddedby(String addedby) {
		this.addedby = addedby;
	}
}
    