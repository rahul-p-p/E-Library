package lib.controler;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.DB.DBConnect;
import lib.dao.RegisterDao;
import lib.model.User;

/**
 * Servlet implementation class registercontroler
 */
@WebServlet("/registercontroler")
public class registercontroler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registercontroler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid= request.getParameter("uname");
		String name= request.getParameter("fullname");
		String email= request.getParameter("email");
		String password= request.getParameter("pass");
		String compassword= request.getParameter("cpass");
		RequestDispatcher rd = null;
		if(password.equals(compassword)) {
			User us= new User();
			us.setUserid(userid);
			us.setName(name);
			us.setEmail(email);
			us.setPassword(password);
			
			RegisterDao dao=new RegisterDao(DBConnect.getConnection());
			
			boolean f=dao.userRegister(us);
			if(f) {
	            request.setAttribute("Message", "Registration Successful! Please login.");
	            rd = request.getRequestDispatcher("login.jsp");
	        } else {
	            request.setAttribute("Message", "Server Error! Try Again.");
	            rd = request.getRequestDispatcher("register.jsp");
	        }
	    } else {
	        request.setAttribute("Message", "Password Mismatch!");
	        rd = request.getRequestDispatcher("register.jsp");
	    }
	    rd.forward(request, response);
	}

}
