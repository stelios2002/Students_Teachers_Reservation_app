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

import ReservationModule.utils.models.Reservation;

public class ReservationDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "L1ok3y20";

	private static final String ACCEPT_RESERVATIONS_SQL = "UPDATE reservations SET accepted = 1 WHERE id = ?;";
	private static final String INSERT_RESERVATION_SQL = "INSERT INTO reservations" 
	+ "  (studid, profid, date, time, room, id, accepted) VALUES (?, ?, ?, ?, ?, ?, ?); ";
	private static final String GET_RESERVATION_SQL = "SELECT * FROM reservations where id = ?;";
	private static final String GET_UNACCEPTED_SQL = "SELECT * FROM reservations where profid = ? AND accepted = 0;";
	private static final String GET_RESERVATIONS_OF_PROFESSOR_SQL = "SELECT * FROM reservations where profid = ?;";
	private static final String GET_RESERVATIONS_OF_STUDENT_SQL = "SELECT * FROM reservations where studid = ?;";
    private static final String DELETE_RESERVATIONS_SQL = "DELETE FROM reservations WHERE id = ?;";
    private static final String EDIT_RESERVATIONS_SQL = "UPDATE reservations SET date = ?, time = ?, room = ? WHERE id = ?;";

    
    
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
			preparedStatement.setString(1, reservation.getStudent());
			preparedStatement.setString(2, reservation.getProfessor());
			SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = isoFormat.parse((reservation.getDate().toString()));
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			preparedStatement.setDate(3, sqlDate);
			Time time = Time.valueOf(reservation.getTime());
			preparedStatement.setTime(4, time);
			preparedStatement.setInt(5, reservation.getRoom());
			preparedStatement.setString(6, reservation.getId());
			preparedStatement.setInt(7, 0);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public ArrayList<Reservation> getReservationsOfProfessor(String id) {
		System.out.println(GET_RESERVATIONS_OF_PROFESSOR_SQL);
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATIONS_OF_PROFESSOR_SQL);) {
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String s1 = resultSet.getString("studid");
				String p1 = resultSet.getString("profid");
				java.sql.Date sqlDate = resultSet.getDate("date");
				LocalDate date = sqlDate.toLocalDate();
				LocalTime time = resultSet.getTime("time").toLocalTime();
				int room = resultSet.getInt("room");
				String rid = resultSet.getString("id");
				boolean accepted = true;
				if(resultSet.getInt("accepted") != 0) {
					Reservation r1 = new Reservation(s1, p1, date, time, room, rid, accepted);
					reservations.add(r1);
				}
			}
			System.out.println(preparedStatement);
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		return reservations;
	}
	
	public ArrayList<Reservation> getReservationsOfStudent(String id) {
		System.out.println(GET_RESERVATIONS_OF_STUDENT_SQL);
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATIONS_OF_STUDENT_SQL);) {
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String s1 = resultSet.getString("studid");
				String p1 = resultSet.getString("profid");
				java.sql.Date sqlDate = resultSet.getDate("date");
				LocalDate date = sqlDate.toLocalDate();
				LocalTime time = resultSet.getTime("time").toLocalTime();
				int room = resultSet.getInt("room");
				String rid = resultSet.getString("id");
				boolean accepted = true;
				if(resultSet.getInt("accepted") == 0) {
					accepted = !accepted;
				}
				Reservation r1 = new Reservation(s1, p1, date, time, room, rid, accepted);
				reservations.add(r1);
			}
			System.out.println(preparedStatement);
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		return reservations;
	}
	

	public Reservation getReservation(String id) {
		System.out.println(GET_RESERVATION_SQL);
		Reservation r1 = null;
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATION_SQL);) {
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String s1 = resultSet.getString("studid");
				String p1 = resultSet.getString("profid");
				java.sql.Date sqlDate = resultSet.getDate("date");
				LocalDate date = sqlDate.toLocalDate();
				LocalTime time = resultSet.getTime("time").toLocalTime();
				int room = resultSet.getInt("room");
				String rid = resultSet.getString("id");
				boolean accepted = true;
				if(resultSet.getInt("accepted") == 0) {
					accepted = !accepted;
				}
				r1 = new Reservation(s1, p1, date, time, room, rid, accepted);
			}
			System.out.println(preparedStatement);
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		return r1;
	}
	
	
	public void deleteReservationsOfStudent(String id) throws SQLException {
		ArrayList<Reservation> reservations = getReservationsOfStudent(id);
		for (int i = 0; i<reservations.size(); i++) {
			try (Connection connection = getConnection();
	        		PreparedStatement idPreparedStatement = connection.prepareStatement(DELETE_RESERVATIONS_SQL)) {
				idPreparedStatement.setString(1, reservations.get(i).getId());
				idPreparedStatement.execute();
			}
		}
	}
	
	
	public void deleteReservationsOfProfessor(String id) throws SQLException {
		ArrayList<Reservation> reservations = getReservationsOfProfessor(id);
		for (int i = 0; i<reservations.size(); i++) {
			try (Connection connection = getConnection();
	        		PreparedStatement idPreparedStatement = connection.prepareStatement(DELETE_RESERVATIONS_SQL)) {
				idPreparedStatement.setString(1, reservations.get(i).getId());
				idPreparedStatement.execute();
			}
		}
	}

	
	public ArrayList<Reservation> getUnacceptedReservations(String id) {
		System.out.println(GET_UNACCEPTED_SQL);
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_UNACCEPTED_SQL);) {
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String s1 = resultSet.getString("studid");
				String p1 = resultSet.getString("profid");
				java.sql.Date sqlDate = resultSet.getDate("date");
				LocalDate date = sqlDate.toLocalDate();
				LocalTime time = resultSet.getTime("time").toLocalTime();
				int room = resultSet.getInt("room");
				String rid = resultSet.getString("id");
				boolean accepted = true;
				if(resultSet.getInt("accepted") == 0) {
					accepted = !accepted;
				}
				Reservation r1 = new Reservation(s1, p1, date, time, room, rid, accepted);
				reservations.add(r1);
			}
			System.out.println(preparedStatement);
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		return reservations;
	}

	
	public void acceptReservation(String id) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ACCEPT_RESERVATIONS_SQL)){
			preparedStatement.setString(1, id);
			preparedStatement.execute();
		}
	}
	
	public void deleteReservation(String id) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATIONS_SQL)){
			preparedStatement.setString(1, id);
			preparedStatement.execute();
		}
	}
	
	public void editReservation(Reservation reservation) throws SQLException, ParseException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(EDIT_RESERVATIONS_SQL)) {
			SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = isoFormat.parse((reservation.getDate().toString()));
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			preparedStatement.setDate(1, sqlDate);
			Time time = Time.valueOf(reservation.getTime());
			preparedStatement.setTime(2, time);
			preparedStatement.setInt(3, reservation.getRoom());
			preparedStatement.setString(4, reservation.getId());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
