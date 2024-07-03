package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ReservationModule.users.dao.ProfessorDao;
import ReservationModule.utils.dao.ReservationDao;
import ReservationModule.utils.models.Reservation;
import ReservationModule.users.models.Professor;
import ReservationModule.users.dao.StudentDao;


public class ProfessorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfessorDao professorDao;
    private ReservationDao reservationDao;
    private StudentDao studentDao;

    public void init() {
        professorDao = new ProfessorDao();
        reservationDao = new ReservationDao();
        studentDao = new StudentDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "registerProfessor":
                    insertProfessor(request, response);
                    break;
                case "acceptReservation":
                    acceptReservation(request, response);
                    break;
                case "showReservations":
                	showReservations(request, response);
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void acceptReservation(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void showReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("hidden_id");
		ArrayList<Reservation> programs = reservationDao.getReservationsOfProfessor(id);
	    request.setAttribute("programs", programs);
	    request.getRequestDispatcher("/ShowReservations.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Define GET request handling if needed
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
}