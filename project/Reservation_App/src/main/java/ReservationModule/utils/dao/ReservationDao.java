package ReservationModule.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ReservationModule.utils.models.Reservation;

public class ReservationDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_RESERVATION_SQL = "INSERT INTO reservations" 
	+ "  (studid, profid, date, time, room, id) VALUES (?, ?, ?, ?, ?, ?); ";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
}
