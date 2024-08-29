package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ReservationModule.users.dao.AdminDao;
import ReservationModule.users.dao.Ipassword;
import ReservationModule.users.dao.ProfessorDao;
import ReservationModule.users.dao.StudentDao;
import ReservationModule.users.dao.UserDao;
import ReservationModule.users.models.Admin;
import ReservationModule.users.models.Professor;
import ReservationModule.users.models.Student;
import ReservationModule.utils.dao.ReservationDao;
import ReservationModule.utils.models.Reservation;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDao();
	private StudentDao studentDao = new StudentDao();
	private ProfessorDao professorDao = new ProfessorDao();
	private ReservationDao reservationDao = new ReservationDao();
	private UserDao userDao = new UserDao();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			switch (action) {
			case "/new":
				//showNewForm(request, response);
				break;
			case "/insert_student":
				insertStudent(request, response);
				break;
			case "/insert_admin":
				insertAdmin(request, response);
				break;
			case "/insert_professor":
				insertProfessor(request, response);
				break;
			case "/delete_user":
				deleteUser(request, response);
				break;
			case "/delete_reservation":
				deleteReservation(request, response);
				break;
			case "/show_reservations":
				showReservations(request, response);
				break;
			default:
				//listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void insertAdmin(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = Integer.parseInt(request.getParameter("name"));
	    String hashedPassword = Ipassword.hashPassword(password);
		Admin newAdmin = new Admin(username, name, surname, hashedPassword, role);
		adminDao.insertAdmin(newAdmin);
		response.sendRedirect("list");
	}
	
	private void insertStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String username = request.getParameter("username");
	    String name = request.getParameter("name");
	    String surname = request.getParameter("surname");
	    String password = request.getParameter("password");
	    int role = 2;
	    String dept = request.getParameter("dept");
	    String school = request.getParameter("school");
	    int year = Integer.parseInt(request.getParameter("year"));
	    String id = request.getParameter("id");
	    Student newStudent = new Student(username, password, name, surname, role, dept, school, year, id);
	    studentDao.insertStudent(newStudent);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminMain.jsp");
	    dispatcher.forward(request, response);
	}
	
	private void insertProfessor(HttpServletRequest request, HttpServletResponse response) 
	        throws SQLException, IOException, ServletException {
	    String username = request.getParameter("username");
	    String name = request.getParameter("name");
	    String surname = request.getParameter("surname");
	    String password = request.getParameter("password");
	    int role = 3;
	    String dept = request.getParameter("dept");
	    String school = request.getParameter("school");
	    String speciality = request.getParameter("specialty");
	    String id = request.getParameter("id");
	    Professor newProfessor = new Professor(username, password, name, surname, role, dept, school, speciality, id);
	    professorDao.insertProfessor(newProfessor);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("AdminMain.jsp");
	    dispatcher.forward(request, response);
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String username = request.getParameter("username");
		userDao.deleteUser(username);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminMain.jsp");
	    dispatcher.forward(request, response);
	}
	
	private void showReservations(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException, ServletException {
		ArrayList<Reservation> programs = reservationDao.getReservations();
	    request.setAttribute("programs", programs);
	    request.getRequestDispatcher("/ShowReservations.jsp").forward(request, response);
	}
	
	private void deleteReservation(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String id = request.getParameter("id");
		reservationDao.deleteReservation(id);
		response.sendRedirect("ShowReservations.jsp");
	}
	
	

}