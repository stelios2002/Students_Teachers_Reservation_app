package ReservationModule.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import ReservationModule.users.dao.ProfessorDao;
import ReservationModule.users.dao.StudentDao;
import ReservationModule.users.models.Professor;
import ReservationModule.users.models.Student;
import ReservationModule.utils.models.Reservation;

public class ReservationDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "L1ok3y20";

	private static final String INSERT_RESERVATION_SQL = "INSERT INTO reservations" 
	+ "  (studid, profid, date, time, room, id) VALUES (?, ?, ?, ?, ?, ?); ";
	private static final String GET_RESERVATIONS_SQL = "SELECT * FROM reservations;";

	protected Connection getConnection() {
        Connection connection = null;
        try {
        	 System.out.println("Loading driver class...");
             Class.forName("com.mysql.cj.jdbc.Driver");
             // Print debug statement
             System.out.println("Driver loaded successfully!");

             connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             // Print debug statement
             System.out.println("Connection established successfully!");
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class Not Found Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

	public void insertReservation(Reservation reservation) throws SQLException, ParseException {
		System.out.println(INSERT_RESERVATION_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERVATION_SQL)) {
			preparedStatement.setString(1, reservation.getStudent().getId());
			preparedStatement.setString(2, reservation.getProfessor().getId());
			SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");//uuuu-MM-dd
			java.util.Date date = isoFormat.parse((reservation.getDate().toString()));
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			preparedStatement.setDate(3, sqlDate);
			Time time = Time.valueOf(reservation.getTime());
			preparedStatement.setTime(4, time);
			preparedStatement.setInt(5, reservation.getRoom());
			preparedStatement.setString(6, reservation.getId());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void deleteReservation(String id) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Reservation> getReservations() {
		System.out.println(GET_RESERVATIONS_SQL);
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATIONS_SQL);
			ResultSet resultSet = preparedStatement.executeQuery()) {
			
			while(resultSet.next()) {
				String sid = resultSet.getString("studid");
				String pid = resultSet.getString("profid");
				java.sql.Date sqlDate = resultSet.getDate("date");
				LocalDate date = sqlDate.toLocalDate();
				LocalTime time = resultSet.getTime("time").toLocalTime();
				int room = resultSet.getInt("room");
				String id = resultSet.getString("id");
				StudentDao studentDao = new StudentDao();
				ProfessorDao professorDao = new ProfessorDao();
				Student s1 = studentDao.getStudent(sid);
				Professor p1 = professorDao.getProfessor(pid);
				Reservation r1 = new Reservation(s1, p1, date, time, room, id);
				reservations.add(r1);
			}
			System.out.println(preparedStatement);
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		return reservations;
	}
}
