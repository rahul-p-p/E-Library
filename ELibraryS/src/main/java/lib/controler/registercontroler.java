package lib.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		HttpSession session=request.getSession();
		
		if(password.equals(compassword)) {
			User us= new User();
			us.setUserid(userid);
			us.setName(name);
			us.setEmail(email);
			us.setPassword(password);
			
			RegisterDao dao=new RegisterDao(DBConnect.getConnection());
			
			boolean f=dao.userRegister(us);
			if(f) {
	            session.setAttribute("success", "Registration Successful! Please login.");
	            response.sendRedirect("register.jsp");
	        } else {
	        	session.setAttribute("error", "Server Error! Try Again.");
	        	response.sendRedirect("register.jsp");
	        }
	    } else {
	    	session.setAttribute("error", "Password Mismatch!");
	    	response.sendRedirect("register.jsp");
	    }
	}

}
