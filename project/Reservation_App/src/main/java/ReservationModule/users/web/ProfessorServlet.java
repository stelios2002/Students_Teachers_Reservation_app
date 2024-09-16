package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ReservationModule.users.dao.ProfessorDao;
import ReservationModule.utils.dao.AvailabilityDao;
import ReservationModule.utils.dao.ReservationDao;
import ReservationModule.utils.models.Reservation;
import ReservationModule.users.models.Professor;
import ReservationModule.users.dao.Ipassword;


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
                    case "updateReservation":
                    	updateReservation(request, response);
                        break;
                    case "deleteReservation":
                        deleteReservation(request, response);
                        break;    
                    case "Reservations":
                    	showReservations(request, response);
                    	break;
                    case "Confirm Reservations":
                    	showUnacceptedReservations(request, response);
                    	break;
                    case "Search Student":
                    	showUnacceptedReservations(request, response);
                    	break;
                    case "Info Providing":
                    	showUnacceptedReservations(request, response);
                    	break;
                    case "Confirm":
                    	editReservation(request, response);
                    	break;
                    case "SetAvailability":
                    	setAvailability(request, response);
                    	break;
                    case "Set Availability":
                    	goToAvailability(request, response);
                    	break;
                    default:
                        response.sendRedirect("index.jsp");
                        break;
                }
            } catch (SQLException | ParseException e) {
                throw new ServletException(e);
            }
    }

	 private void goToAvailability(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("SetAvailability.jsp");
			dispatcher.forward(request, response);
		}

		private void setAvailability(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			String day = request.getParameter("dayOfWeek");
			boolean[] timeslots = {false, false, false, false,
					false, false, false, false,
					false, false, false, false,
					false, false, false, false};
			String[] Selected = request.getParameterValues("timeSlots");
			int[] s = new int[Selected.length];
			for (int i = 0; i< Selected.length; i++) {
				s[i] = Integer.parseInt(Selected[i]);
			}
			for (int i : s) {
				timeslots[i] = true;
			}
			Professor professor;
			HttpSession session = request.getSession();
			if((String) session.getAttribute("username") != null) {
				professor = professorDao.getProfessor((String) session.getAttribute("username"));
				AvailabilityDao availabilityDao = new AvailabilityDao();
				if(availabilityDao.isRegisteredAvailable(day, professor)) {
					availabilityDao.updateAvailability(day, timeslots, professor);
				} else {
					availabilityDao.setAvailability(day, timeslots, professor);
				}
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
			showUnacceptedReservations(request,response);
		}
			

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	String id = request.getParameter("reservationId");
		reservationDao.deleteReservation(id);
		showUnacceptedReservations(request, response);		
	}
    
    private void updateReservation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	String id = request.getParameter("reservationId");
		Reservation r = reservationDao.getReservation(id);
		request.setAttribute("reservation", r);
		request.setAttribute("type", "professor");
		RequestDispatcher dispatcher = request.getRequestDispatcher("EditReservation.jsp");
		dispatcher.forward(request, response);
	}
    
    private void editReservation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
    	String studentId = request.getParameter("studid");
	    String professorId = request.getParameter("profid");
	    LocalDate date = LocalDate.parse(request.getParameter("date"));
	    LocalTime time = LocalTime.parse(request.getParameter("time"));
	    int room = Integer.parseInt(request.getParameter("room"));
	    String reservationId = request.getParameter("id");
	    int priority = Integer.parseInt(request.getParameter("priority"));
	    String comment = request.getParameter("comment");
	    
	    System.out.println("I am going inside");
	    // Create Reservation object
	    Reservation reservation = new Reservation(studentId, professorId, date, time, room, reservationId, false, priority, comment);
	    
	    reservationDao.editReservation(reservation);
	    showReservations(request, response);
    }
    
	private void acceptReservation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String id = request.getParameter("reservationId");
		reservationDao.acceptReservation(id);
		showUnacceptedReservations(request, response);		
	}
    
    private void showUnacceptedReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		if((String) session.getAttribute("username") != null) {
			List<Reservation> reservations = reservationDao.getUnacceptedReservations(professorDao.getProfessor((String) session.getAttribute("username")).getId());
			request.setAttribute("reservations", reservations);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("ProfessorMain.jsp");
		dispatcher.forward(request, response);
	}
    
	private void showReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReservationDao reservationDao = new ReservationDao();
		ProfessorDao professorDao = new ProfessorDao();
		HttpSession session = request.getSession();
		if((String) session.getAttribute("username") != null) {
			List<Reservation> reservations = reservationDao.getReservationsOfProfessor(professorDao.getProfessor((String) session.getAttribute("username")).getId());
			request.setAttribute("reservations", reservations);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("AcceptedReservations.jsp");
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
	    String hashedPassword = Ipassword.hashPassword(password);
        Professor newProfessor = new Professor(username, hashedPassword, name, surname, role, dept, school, speciality, id);
        professorDao.insertProfessor(newProfessor);
        response.sendRedirect("index.jsp");
    }
}
