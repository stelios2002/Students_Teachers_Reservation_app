package ReservationModule.users.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ReservationModule.users.dao.ProfessorDao;
import ReservationModule.utils.dao.ReservationDao;
import ReservationModule.utils.models.Reservation;
import ReservationModule.users.models.Professor;
import ReservationModule.users.models.Student;
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
                case "registerProfessor":
                    insertProfessor(request, response);
                    break;
                case "newReservation":
                    commitReservation(request, response);
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (SQLException | ParseException e) {
            throw new ServletException(e);
        }
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
}

