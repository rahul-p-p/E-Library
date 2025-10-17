package lib.controler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.websocket.server.WsRemoteEndpointImplServer;

import lib.DB.DBConnect;
import lib.dao.RegisterDao;
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
		RegisterDao dao= new RegisterDao(DBConnect.getConnection());
		
		HttpSession session = request.getSession();
		
		String userid= request.getParameter("uname");
		String password= request.getParameter("pass");
		try {
			User user =dao.userLogin(userid, password);
			if(user!=null) {
				if(user.getType().equals("user")) {
					session.setAttribute("User", user);
					response.sendRedirect("home.jsp");
				}else{
					session.setAttribute("User", user);
					response.sendRedirect("index2.jsp");
				}
				
			}else{
				session.setAttribute("Error", "incorect User or password");
				response.sendRedirect("login.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
