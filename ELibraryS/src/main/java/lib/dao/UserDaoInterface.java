package lib.dao;

import lib.model.User;

public interface UserDaoInterface {
	public boolean userRegister(User us);
	
	public User userLogin(String uname,String pass);

}
