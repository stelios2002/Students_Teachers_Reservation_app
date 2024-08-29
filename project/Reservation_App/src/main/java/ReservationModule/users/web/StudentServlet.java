package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
import ReservationModule.users.dao.Ipassword;

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
	                case "commitReservation":
	                	commitReservation(request, response);
	                // Add more cases as needed
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
	 
	 private void showReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String id = request.getParameter("hidden_id");
			ArrayList<Reservation> programs = reservationDao.getReservationsOfStudent(id);
		    request.setAttribute("programs", programs);
		    request.getRequestDispatcher("StudentReservations.jsp").forward(request, response);
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
		    Student student = studentDao.getStudentByID(studentId);
		    Professor professor = professorDao.getProfessorByID(professorId);
		    
		    // Check if student is null before using it
		    if (student == null) {
		        throw new ServletException("Student with ID " + studentId + " not found");
		    }
		    
		    if (professor == null) {
		        throw new ServletException("Student with ID " + studentId + " not found");
		    }
		    
		    // Create Reservation object
		    Reservation reservation = new Reservation(student, professor, date, time, room, reservationId);
		    
		    // Insert Reservation into database
		    reservationDao.insertReservation(reservation);
		    
		    response.sendRedirect("StudentMain.jsp");
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
		    String hashedPassword = Ipassword.hashPassword(password);
		    System.out.println(hashedPassword);
		    Student newStudent = new Student(username, hashedPassword, name, surname, role, dept, school, year, id);
		    studentDao.insertStudent(newStudent);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		}
}
