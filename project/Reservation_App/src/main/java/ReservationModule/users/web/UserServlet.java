package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ReservationModule.users.dao.AdminDao;
import ReservationModule.users.dao.ProfessorDao;
import ReservationModule.users.dao.StudentDao;
import ReservationModule.users.dao.UserDao;

public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDao studentDao;
    private ProfessorDao professorDao;
    private UserDao userDao;
    private AdminDao adminDao;
    
    public void init() {
        studentDao = new StudentDao();
        adminDao = new AdminDao();
        professorDao = new ProfessorDao();
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
				Logout(request, response);
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
		System.out.println("Attempting login for user: " + username);
		int role = userDao.getRole(username,password);
		if (role == 2) {
			studentDao.getStudent(username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("StudentMain.jsp");
			dispatcher.forward(request, response);
		} else if (role == 3) {
			professorDao.getProfessor(username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorMain.jsp");
			dispatcher.forward(request, response);
		} else if (role == 1) {
			adminDao.setAdmin(username);
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
	
	public void Logout (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("LogoutPage.jsp");
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
