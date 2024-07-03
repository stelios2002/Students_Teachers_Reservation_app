package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ReservationModule.users.dao.ProfessorDao;
import ReservationModule.utils.dao.ReservationDao;
import ReservationModule.utils.models.Reservation;
import ReservationModule.users.models.Professor;


public class ProfessorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfessorDao professorDao;
    private ReservationDao reservationDao;

    public void init() {
        professorDao = new ProfessorDao();
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
                    case "registerProfessor":
                        insertProfessor(request, response);
                        break;
                    case "acceptReservation":
                        acceptReservation(request, response);
                        break;
                    case "Reservations":
                    	showReservations(request, response);
                    case "Confirm Reservations":
                    	showUnacceptedReservations(request, response);
                    default:
                        response.sendRedirect("index.jsp");
                        break;
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }
    }

    private void acceptReservation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String id = request.getParameter("reservation_id");
		reservationDao.acceptReservation(id);
		showUnacceptedReservations(request, response);		
	}
    
    private void showUnacceptedReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("hidden_id");
		ArrayList<Reservation> programs = reservationDao.getUnacceptedReservations(id);
	    request.setAttribute("programs", programs);
	    request.getRequestDispatcher("ShowReservations.jsp").forward(request, response);
	}
    
	private void showReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("hidden_id");
		ArrayList<Reservation> programs = reservationDao.getReservationsOfProfessor(id);
	    request.setAttribute("programs", programs);
	    request.getRequestDispatcher("ShowReservations.jsp").forward(request, response);
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