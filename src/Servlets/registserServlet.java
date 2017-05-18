package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.UserDAO;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidMobileNumberException;
import Exceptions.InvalidNameException;
import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUserLoginException;
import Exceptions.InvalidUserNameException;
import Model.Platform;
import Model.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class registserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String gender = request.getParameter("gender");
	
		//String birthday = request.getParameter("birthday");
		
//		DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
//		Date result = null;
//		try {
//			result = dateFormat.parse(birthday);
//		} catch (ParseException e1) {
//			System.out.println("Error in parsing birthday - " + e1.getMessage());
//		}
//		
//		System.out.println(result);
		
//		Date birthday = null;
//		
//		try {
//			birthday = new SimpleDateFormat("mm/dd/yyyy").parse(request.getParameter("birthday"));
//		} catch (ParseException e) {
//			System.out.println("Couldn't parse.");
//		}
//		
		String mobileNumber = request.getParameter("mobileNumber");
		
		//some problems here with the parametes
		
		User user = null;
			try {
				user = new User(name, username, password, email, city, null, gender, mobileNumber);
			} catch (InvalidUserLoginException e) {
				System.out.println(e.getMessage());
			} catch (InvalidEmailException e) {
				System.out.println(e.getMessage());
			} catch (InvalidPasswordException e) {
				System.out.println(e.getMessage());
			} catch (InvalidMobileNumberException e) {
				System.out.println(e.getMessage());
			} catch (InvalidNameException e) {
				System.out.println(e.getMessage());
			} catch (InvalidUserNameException e) {
				System.out.println(e.getMessage());
			}
			
			if(user==null || user.getUsername()==null || user.getUsername().isEmpty() || user.getEmail()==null ||
					user.getCity()==null || user.getGender()==null || user.getMobileNumber()==null){
				RequestDispatcher view = request.getRequestDispatcher("errorRegPage.html"); //TODO
				view.forward(request, response);
			}
			else{
				if (!UserDAO.getInstance().saveUser(user)) {
					System.out.println("SMT wrong.");
					RequestDispatcher view = request.getRequestDispatcher("errorRegPage.html"); //TODO
					view.forward(request, response);
				}
				else{
					RequestDispatcher view2 = request.getRequestDispatcher("loginPagee.html");
					view2.forward(request, response);
				}
			}

		
	}

}
