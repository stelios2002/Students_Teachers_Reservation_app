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
import javax.servlet.http.HttpSession;

import ReservationModule.users.dao.ProfessorDao;
import ReservationModule.users.dao.StudentDao;
import ReservationModule.users.dao.UserDao;
import ReservationModule.users.models.Admin;
import ReservationModule.users.models.Professor;
import ReservationModule.users.models.Student;
import ReservationModule.users.models.User;
import ReservationModule.utils.dao.ReservationDao;
import ReservationModule.utils.models.Reservation;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDao studentDao;
    private ProfessorDao professorDao;
    private ReservationDao reservationDao;
    private UserDao userDao;
    private AdminDao adminDao;
    private Student currentStudent;
    private Professor currentProfessor;
    private Admin currentAdmin;
    
    public void init() {
        studentDao = new StudentDao();
        adminDao = new AdminDao();
        professorDao = new ProfessorDao();
        reservationDao = new ReservationDao();
        userDao = new UserDao();
    }

	private LocalDate convertToLocalDateViaMilisecond(java.util.Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "registerStudent":
                    insertStudent(request, response);
                    break;
                case "newReservation":
                    commitReservation(request, response);
                    break;
                case "deleteUser":
                    deleteUser(request, response);
                    break;
                case "registerProfessor":
                    insertProfessor(request, response);
                    break;
                case "deleteReservation":
                    deleteReservation(request, response);
                    break;
                case "loginUser":
                	loginUser(request,response);
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "listUser":
                    listUser(request, response);
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
	
	private void deleteReservation(HttpServletRequest request, HttpServletResponse response) 
	        throws SQLException, IOException {
	    String id = request.getParameter("id");
	    int role = Integer.parseInt(request.getParameter("role"));
	    reservationDao.deleteReservation(id);
	    if (role == 1) {
	        response.sendRedirect("AdminMain.jsp");
	    } else if (role == 2) {
	    	response.sendRedirect("StudentMain.jsp");
	    } else if (role == 3) {
	    	response.sendRedirect("ProfessorMain.jsp");
	    }
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
	
	private void insertProfessor(HttpServletRequest request, HttpServletResponse response) 
	        throws SQLException, IOException {
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
	    response.sendRedirect("index.jsp");
	}

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
        System.out.println("Attempting login for user: " + username);
    	int role = userDao.getRole(username,password);
    	if (role == 2) {
    		currentStudent = studentDao.setStudent(username);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("StudentMain.jsp");
		    dispatcher.forward(request, response);
		} else if (role == 3) {
			currentProfessor = professorDao.setProfessor(username);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorMain.jsp");
		    dispatcher.forward(request, response);
		} else if (role == 1) {
			currentAdmin = adminDao.setAdmin(username);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminMain.jsp");
		    dispatcher.forward(request, response);
		} else if (role == 0){
			HttpSession session = request.getSession();
            session.setAttribute("error", "Invalid username or password");
            response.sendRedirect("LoginPage.jsp");
		} else {
			//error code
		}
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
	        throws SQLException, IOException {
	    String username = request.getParameter("username");
	    userDao.deleteUser(username);
	    response.sendRedirect("admin_main.jsp");
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
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    List<User> listUser = userDao.selectAllUsers();
	    request.setAttribute("listUser", listUser);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
	    dispatcher.forward(request, response);
	}
	
	// Add more methods as needed
}
