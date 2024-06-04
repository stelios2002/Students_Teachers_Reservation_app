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

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDao studentDao;
    private ProfessorDao professorDao;
    private ReservationDao reservationDao;
    private UserDao userDao;
    
    public void init() {
        studentDao = new StudentDao();
        professorDao = new ProfessorDao();
        reservationDao = new ReservationDao();
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "registerStudent":
                    registerStudent(request, response);
                    break;
                case "registerProfessor":
                    registerProfessor(request, response);
                    break;
                case "deleteReservation":
                    deleteReservation(request, response);
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

    private void registerStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String first_name = request.getParameter("first_name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        int role = 2;
        String dept = request.getParameter("dept");
        String school = request.getParameter("school");
        int year = Integer.parseInt(request.getParameter("year"));
        String id = request.getParameter("id");
        Student newStudent = new Student(username, password, first_name, surname, role, dept, school, year, id);
        studentDao.insertStudent(newStudent);
        response.sendRedirect("index.jsp");
    }
    private void registerProfessor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String first_name = request.getParameter("first_name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        int role = 3;
        String dept = request.getParameter("dept");
        String school = request.getParameter("school");
        String specialty = request.getParameter("specialty");
        String id = request.getParameter("id");
        Professor newProfessor = new Professor(username, password, first_name, surname, role, dept, school, specialty, id);
        professorDao.insertProfessor(newProfessor);
        response.sendRedirect("index.jsp");
    }

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String id = request.getParameter("id");
        reservationDao.deleteReservation(id);
        response.sendRedirect("list");
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

