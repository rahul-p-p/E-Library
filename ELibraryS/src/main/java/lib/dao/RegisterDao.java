package lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
	
	
	

}
