package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ReservationModule.users.dao.StudentDao;
import ReservationModule.users.models.Professor;
import ReservationModule.users.models.Student;
import ReservationModule.utils.dao.ReservationDao;
import ReservationModule.utils.models.Reservation;
import ReservationModule.users.dao.Ipassword;
import ReservationModule.users.dao.ProfessorDao;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
	private ReservationDao reservationDao;
	
	public void init() {
		studentDao = new StudentDao();
        reservationDao = new ReservationDao();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 doGet(request, response);
	}
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");
	        try {
	            switch (action) {
	                case "register":
	                	System.out.println("Register action called");
		                insertStudent(request, response);
		                break;
	                case "Reservations":
	                	showReservations(request, response);
	                    break;
	              
	                case "Info Providing":
	                	showReservations(request, response);
	                    break;
	                case "commitReservation":
	                	commitReservation(request, response);
	                    break;
	                case "deleteReservation":
	                	deleteReservation(request, response);
	                    break;
	                case "editReservation":
	                	editReservation(request, response);
	                    break;
	                case "Show Professors":
	                	showProfessors(request, response);
	                	break;
	                default:
	                    response.sendRedirect("index.jsp");
	                    break;
	            }
	        } catch (SQLException e) {
	            throw new ServletException(e);
	        } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	private void showAvailableDates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   ReservationDao reservationDao = new ReservationDao();
	       List<String> availableDays = reservationDao.getAvailableDays();
	        
	        // Generate dates for the available days
	        
	        Map<String, List<LocalDate>> availableDates = reservationDao.generateAvailableDates(availableDays);
	        
	        // Set the data in the request scope
	        request.setAttribute("availableDates", availableDates);
	        
	        // Forward to JSP
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Reservation.jsp");
			dispatcher.forward(request, response);
	}
	
	private void showProfessors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		ProfessorDao professorDao = new ProfessorDao();
		StudentDao studentDao = new StudentDao();
		HttpSession session = request.getSession();
		if((String) session.getAttribute("username") != null) {
			ArrayList<Professor> professors = professorDao.getProfessors();
			request.setAttribute("professors", professors);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("ShowProfessors.jsp");
		dispatcher.forward(request, response);
	}

	private void showReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ReservationDao reservationDao = new ReservationDao();
			StudentDao studentDao = new StudentDao();
			HttpSession session = request.getSession();
			if((String) session.getAttribute("username") != null) {
				List<Reservation> reservations = reservationDao.getReservationsOfStudent(studentDao.getStudent((String) session.getAttribute("username")).getId());
				request.setAttribute("reservations", reservations);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("StudentMain.jsp");
			dispatcher.forward(request, response);
	}
	 
	 private void commitReservation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException, ServletException {
		    // Retrieve parameters and parse them
		 	HttpSession session = request.getSession();
			if((String) session.getAttribute("username") != null) {
				String username = (String) session.getAttribute("username");
			    String studentId = studentDao.getStudent(username).getId();
			    String professorId = request.getParameter("professor_id");
			    LocalDate date = LocalDate.parse(request.getParameter("date"));
			    LocalTime time = LocalTime.parse(request.getParameter("time"));
			    int room = Integer.parseInt(request.getParameter("room"));
			    String reservationId = UUID.randomUUID().toString();
			    while(!reservationDao.checkAvailability(reservationId)) {
			    	reservationId = UUID.randomUUID().toString();
			    }
			    // Create Reservation object
			    int priority = Integer.parseInt(request.getParameter("priority"));
			    String comment = request.getParameter("comment");

			    // Create Reservation object
			    Reservation reservation = new Reservation(studentId, professorId, date, time, room, reservationId, false, priority, comment);
			    // Insert Reservation into database
			    reservationDao.insertReservation(reservation);
			}		    
		    showReservations(request, response);
	}
	 
	 private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
			String username = request.getParameter("username");
		    String name = request.getParameter("name");
		    String surname = request.getParameter("surname");
		    String password = request.getParameter("password");
		    int role = 2;
		    String dept = request.getParameter("dept");
		    String school = request.getParameter("school");
		    int year = Integer.parseInt(request.getParameter("year"));
		    String id = request.getParameter("id");
		    String hashedPassword = Ipassword.hashPassword(password);
		    System.out.println(hashedPassword);
		    Student newStudent = new Student(username, hashedPassword, name, surname, role, dept, school, year, id);
		    studentDao.insertStudent(newStudent);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
	 }
	 
	 
	 private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			String id = request.getParameter("id");
			reservationDao.deleteReservationsOfStudent(id);
			response.sendRedirect("StudentMain.jsp");
	 }
	 
	 
	 private void editReservation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		    String id = request.getParameter("id");
			reservationDao.deleteReservationsOfStudent(id);
			response.sendRedirect("StudentMain.jsp");	
	 }
}
