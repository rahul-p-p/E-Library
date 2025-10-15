package lib.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.dao.LoginDao;
import lib.model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid= request.getParameter("uname");
		String password= request.getParameter("pass");
		
		User user = new LoginDao().checkLogin(userid, password);
		RequestDispatcher rd = null;
		if(user==null) {
			request.setAttribute("Error", "incorect User or password");
			rd = request.getRequestDispatcher("/login.jsp");
		}else{
			request.setAttribute("User", user);
			rd = request.getRequestDispatcher("/home.jsp");
		}
		rd.forward(request, response);
	}
}
