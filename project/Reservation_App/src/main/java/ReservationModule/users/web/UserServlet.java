package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ReservationModule.users.dao.UserDao;
import ReservationModule.utils.dao.ReservationDao;
import ReservationModule.utils.models.Reservation;
import ReservationModule.users.dao.Ipassword;
import ReservationModule.users.dao.StudentDao;

public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;
    
    public void init() {
        userDao = new UserDao();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "login":
				login(request, response);
				break;
			case "Logout":
				logout(request, response);
				break;	
			
			case "UserDelete":
				deleteUser(request,response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			    dispatcher.forward(request, response);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	public void login (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPassword = Ipassword.hashPassword(password);
		System.out.println("Attempting login for user: " + username);
		int role = userDao.login(username,hashedPassword);
		if (role == 2) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			System.out.println(session.getAttribute("username") + "tries to login");
			ReservationDao reservationDao = new ReservationDao();
			StudentDao studentDao = new StudentDao();
			List<Reservation> reservations = reservationDao.getReservationsOfStudent(studentDao.getStudent(username).getId());
			request.setAttribute("reservations", reservations);
			RequestDispatcher dispatcher = request.getRequestDispatcher("StudentMain.jsp");
			dispatcher.forward(request, response);
		} else if (role == 3) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorMain.jsp");
			dispatcher.forward(request, response);
		} else if (role == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminMain.jsp");
			dispatcher.forward(request, response);
		} else if (role == -1){
			HttpSession session = request.getSession();
			session.setAttribute("error", "Invalid username");
			response.sendRedirect("LoginPage.jsp");
		} else if (role == 0){
			HttpSession session = request.getSession();
			session.setAttribute("error", "Invalid password");
			response.sendRedirect("LoginPage.jsp");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("error", "Runtime Error: refer to console log");
			response.sendRedirect("LoginPage.jsp");
		}
	}
	
	public void logout (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // Invalidate the session
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
	        throws SQLException, IOException, ServletException {
	    String username = request.getParameter("username");
	    userDao.deleteUser(username);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("adminMain.jsp");
	    dispatcher.forward(request, response);
	}
	
	// Add more methods as needed
}
