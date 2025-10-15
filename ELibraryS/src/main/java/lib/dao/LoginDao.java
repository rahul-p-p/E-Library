package lib.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lib.model.User;

public class LoginDao {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/elibrary", "root", "2004");
    }

   
    public User checkLogin(String userid, String password) {
        User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT uname, pass, type FROM login WHERE uname = ? AND pass = ?");
            ps.setString(1, userid);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserid(rs.getString("uname")); 
                user.setPassword(rs.getString("pass"));  
                user.setType(rs.getString("type")); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
