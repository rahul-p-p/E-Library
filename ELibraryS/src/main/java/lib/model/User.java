package lib.model;

public class User {
	private String userid;
	private String name;
	private String Email;
    private String password;
    private String type; 
    private String createdat;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreatedat() {
		return createdat;
	}
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}
	public User(String userid, String name, String email, String password, String type, String createdat) {
		super();
		this.userid = userid;
		this.name = name;
		Email = email;
		this.password = password;
		this.type = type;
		this.createdat = createdat;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
