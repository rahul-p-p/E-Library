package lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lib.model.User;

public class RegisterDao implements UserDaoInterface{
	private Connection con;

	public RegisterDao(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public boolean userRegister(User us) {
		boolean f=false;
		try {
			PreparedStatement ps = con.prepareStatement("insert into users(uname,name,email,pass) values(?,?,?,?)");
			ps.setString(1,us.getUserid());
			ps.setString(2,us.getName());
			ps.setString(3,us.getEmail());
			ps.setString(4,us.getPassword());
			
			int i=ps.executeUpdate();
			if(i==1) {
				f=true;
			}
			
		} catch (Exception e) {
			System.out.println("Error while registering user: " + e.getMessage());
	        e.printStackTrace();
		}
		
		return f;
	}

	@Override
	public User userLogin(String uname, String pass) {
		User user=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM users WHERE uname=? AND pass=?");

			ps.setString(1,uname);
			ps.setString(2,pass);
			rs=ps.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setName(rs.getString("name")); 
                user.setUserid(rs.getString("uname")); 
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass"));
                user.setType(rs.getString("role")); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	

}
