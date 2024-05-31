package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ReservationModule.users.dao.ProfessorDao;
import ReservationModule.users.dao.StudentDao;
import ReservationModule.users.dao.UserDao;
import ReservationModule.users.models.Professor;
import ReservationModule.users.models.Student;
import ReservationModule.users.models.User;
import ReservationModule.utils.dao.ReservationDao;
import ReservationModule.utils.models.Reservation;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao = new StudentDao();
	private ProfessorDao professorDao = new ProfessorDao();
	private ReservationDao reservationDao = new ReservationDao();
	private UserDao userDao = new UserDao();
	

	public LocalDate convertToLocalDateViaMilisecond(java.util.Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert_phoneNumber":
				commitReservation(request, response);
				break;
			case "/delete_user":
				deleteUser(request, response);
				break;
			case "/delete_phoneNumber":
				deleteReservation(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/register_client":
				insertStudent(request, response);
				break;
			case "/register_seller":
				insertProfessor(request, response);
			case "/login":
				login(request, response);
				break;
			case "/logout":
				logout(request, response);
				break;	
			case "/pay_bill":
				pay_bill(request, response);
				break;
			case "/display_account":
				display_account(request, response);
				break;
            case "/display_call_history":
            	display_call_history(request, response);
				break;	
            case "/display_balance":
            	display_balance(request,response);
            	break;
            case "/set_balance":
            	set_balance(request, response);
            	break;
            case "/display_programs":
            	display_programs(request, response);
            	break;
            case "/change_program":	
            	change_program(request, response);
            	break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = UserDao.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}
	
	private void insertStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = 2;
		String dept = request.getParameter("dept");
		String school = request.getParameter("school");
		int year = Integer.parseInt(request.getParameter("year"));
		String id = request.getParameter("id");
		Student newStudent = new Student(username, password, name, surname, role, dept, school, year, id);
		studentDao.insertStudent(newStudent);
		response.sendRedirect("login.jsp");
	}
	
	private void insertProfessor(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("name");
		String name = request.getParameter("name");
		String surname = request.getParameter("name");
		String password = request.getParameter("name");
		int role = 3;
		String dept = request.getParameter("dept");
		String school = request.getParameter("school");
		String speciality = request.getParameter("speciality");
		String id = request.getParameter("id");
		Professor newSeller = new Professor(username, password, name, surname, role, dept, school, speciality, id);
		professorDao.insertProfessor(newSeller);
		response.sendRedirect("login.jsp");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String username = request.getParameter("username");
		UserDao.deleteUser(username);
		response.sendRedirect("list");
	}
	
	private void commitReservation(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ParseException {
		String phoneNumber = request.getParameter("phonenumber");
		int programId = Integer.parseInt(request.getParameter("programId"));
		Student student = studentDao.getStudent(request.getParameter("student_id"));
		Professor professor = professorDao.getProfessor(request.getParameter("professor_id"));;
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");//uuuu-MM-dd
		java.util.Date date = isoFormat.parse(request.getParameter("date"));
		LocalDate myDate = convertToLocalDateViaMilisecond(date);
		LocalTime time = LocalTime.parse(request.getParameter("time"));
		int room = Integer.parseInt(request.getParameter("room"));
		String id = request.getParameter("id");
		Reservation reservation = new Reservation(student, professor, myDate, time, room, id);
		reservationDao.insertReservation(reservation);
		response.sendRedirect("list");
	}
	
	private void deletePhoneNumber(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String phoneNumber = request.getParameter("phonenumber");
		phoneNumberDao.deleteNumber(phoneNumber);
		response.sendRedirect("list");
	}
	
	

}
