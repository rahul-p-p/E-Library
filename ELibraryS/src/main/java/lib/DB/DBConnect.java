package lib.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static Connection con;
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//	        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/elibrary", "root", "2004");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/elibrary", "root", "W7301@jqir#");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return con;
    }

}
