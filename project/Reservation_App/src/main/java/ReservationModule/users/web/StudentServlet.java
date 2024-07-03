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
	                case "commitReservation":
	                    commitReservation(request, response);
	                    break;
	                case "showReservationForm":
	                    showReservationForm(request, response);
	                    break;
	                // Add more cases as needed
	                default:
	                    response.sendRedirect("index.jsp");
	                    break;
	            }
	        } catch (SQLException | ParseException e) {
	            throw new ServletException(e);
	        }
	    }
	 
	 private void commitReservation(HttpServletRequest request, HttpServletResponse response) 
		        throws SQLException, IOException, ParseException, ServletException {
		    // Retrieve parameters and parse them
		    String studentId = request.getParameter("student_id");
		    String professorId = request.getParameter("professor_id");
		    LocalDate date = LocalDate.parse(request.getParameter("date"));
		    LocalTime time = LocalTime.parse(request.getParameter("time"));
		    int room = Integer.parseInt(request.getParameter("room"));
		    String reservationId = request.getParameter("id");
		    
		    // Retrieve Student and Professor objects from DAOs
		    Student student = studentDao.getStudent(studentId);
		    Professor professor = professorDao.getProfessor(professorId);
		    
		    // Check if student is null before using it
		    if (student == null) {
		        throw new ServletException("Student with ID " + studentId + " not found");
		    }
		    
		    // Create Reservation object
		    Reservation reservation = new Reservation(student, professor, date, time, room, reservationId);
		    
		    // Insert Reservation into database
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
	 private void showReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String id;
		ArrayList<Reservation> programs = reservationDao.getReservationsOfProfessor(id);
		request.setAttribute("programs", programs);
		request.getRequestDispatcher("/ShowReservations.jsp").forward(request, response);
	}
}
