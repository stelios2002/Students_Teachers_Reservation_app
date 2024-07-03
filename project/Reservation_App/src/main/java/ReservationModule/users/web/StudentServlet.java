package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ReservationModule.users.dao.ProfessorDao;
import ReservationModule.users.dao.StudentDao;
import ReservationModule.users.models.Professor;
import ReservationModule.users.models.Student;
import ReservationModule.utils.dao.ReservationDao;
import ReservationModule.utils.models.Reservation;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;
	private ReservationDao reservationDao;
	private ProfessorDao professorDao;
	
	public void init() {
		studentDao = new StudentDao();
        reservationDao = new ReservationDao();
        professorDao = new ProfessorDao();
    }

	private LocalDate convertToLocalDateViaMilisecond(java.util.Date dateToConvert) {
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
	        String action = request.getParameter("action");
	        try {
	            switch (action) {
	                case "register":
	                	System.out.println("Register action called");
		                insertStudent(request, response);
		                break;
	                // Add more cases as needed
	                default:
	                    response.sendRedirect("index.jsp");
	                    break;
	            }
	        } catch (SQLException e) {
	            throw new ServletException(e);
	        }
	    }
	 
	 private void commitReservation(HttpServletRequest request, HttpServletResponse response) 
		        throws SQLException, IOException, ParseException {
		    Student student = studentDao.getStudent(request.getParameter("student_id"));
		    Professor professor = professorDao.getProfessor(request.getParameter("professor_id"));
		    SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
		    java.util.Date date = isoFormat.parse(request.getParameter("date"));
		    LocalDate myDate = convertToLocalDateViaMilisecond(date);
		    LocalTime time = LocalTime.parse(request.getParameter("time"));
		    int room = Integer.parseInt(request.getParameter("room"));
		    String id = request.getParameter("id");
		    Reservation reservation = new Reservation(student, professor, myDate, time, room, id);
		    reservationDao.insertReservation(reservation);
		    response.sendRedirect("student_main.jsp");
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
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		}
}
