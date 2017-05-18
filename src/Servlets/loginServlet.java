package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DatabaseManager;
import DB.PlayListDAO;
import DB.UserDAO;
import Model.User;

/**
 * Servlet implementation class loginServlett
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htmlFile = "/registrationPage.html";
		RequestDispatcher view = request.getRequestDispatcher(htmlFile);
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String htmlFile;
			
			if(UserDAO.getInstance().isValidLogin(username, password)){
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("isLogged", true);
//				User user = (User) session.getAttribute("currentSessionUser");
				htmlFile = "genres.jsp";
			}
			else{
				htmlFile = "errorloginPage.html";
			}
			
			RequestDispatcher view = request.getRequestDispatcher(htmlFile);
			view.forward(request, response);
			
		}
	
	
		public void destroy(){
			try {
				DatabaseManager.getInstance().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}


