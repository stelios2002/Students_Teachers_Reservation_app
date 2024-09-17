package ReservationModule.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ReservationModule.users.models.Professor;

public class AvailabilityDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "L1ok3y20";

	private static final String INSERT_PDA_SQL = "INSERT INTO availability (profid, time, day, is_available) VALUES (?, ?, ?, ?)"; //Prof,Day,Avail
	private static final String IS_REGISTERED_PDA_SQL = "SELECT * FROM availability WHERE profid=? AND day=?"; //Prof,Day,Avail
    private static final String EDIT_PDA_SQL = "UPDATE availability SET is_available = ? WHERE profid = ? AND day=? AND time = ?;"; //Prof,Day,Avail
    private static final String GET_PDA_SQL = "SELECT timeslot, is_available FROM availability WHERE profid = ? AND day = ?";
    private static final String GET_AVAILABILITY_SQL = "SELECT day, time, is_available FROM availability WHERE profid = ?";
	
	
	protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class Not Found Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

	public boolean isRegisteredAvailable(String day, Professor professor) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement regStatement = connection.prepareStatement(IS_REGISTERED_PDA_SQL);){
			regStatement.setString(1, professor.getId());
			int d = 1;
			if(day.equals("Tuesday")) {
				d = 2;
			} else if (day.equals("Wednesday")) {
				d = 3;
			} else if (day.equals("Thursday")) {
				d = 4;
			} else if (day.equals("Friday")) {
				d = 5;
			}
			regStatement.setInt(2, d);
			try (ResultSet rsu = regStatement.executeQuery()) {
	            if (rsu.next()) {
	            	return true;
	            }
			}
		}
		return false;
	}

	public void updateAvailability(String day, boolean[] timeslots, Professor professor) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(EDIT_PDA_SQL)) {
			for(int i=0; i<timeslots.length;i++) {
				preparedStatement.setBoolean(1, false);
				if (timeslots[i]) {
					preparedStatement.setBoolean(1, true);
				}
				int d = 1;
				if(day.equals("Tuesday")) {
					d = 2;
				} else if (day.equals("Wednesday")) {
					d = 3;
				} else if (day.equals("Thursday")) {
					d = 4;
				} else if (day.equals("Friday")) {
					d = 5;
				}
				preparedStatement.setString(2, professor.getId());
				preparedStatement.setInt(3, d);
				preparedStatement.setInt(4, i);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void setAvailability(String day, boolean[] timeslots, Professor professor) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PDA_SQL)) {
			for(int i=0; i<timeslots.length;i++) {
				preparedStatement.setBoolean(4, false);
				if (timeslots[i]) {
					preparedStatement.setBoolean(4, true);
				}
				int d = 1;
				if(day.equals("Tuesday")) {
					d = 2;
				} else if (day.equals("Wednesday")) {
					d = 3;
				} else if (day.equals("Thursday")) {
					d = 4;
				} else if (day.equals("Friday")) {
					d = 5;
				}
				preparedStatement.setString(1, professor.getId());
				preparedStatement.setInt(3, d);
				preparedStatement.setInt(2, i);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean[] getAvailability(String day, Professor professor) {
	    boolean[] timeslots = new boolean[16]; // 16 time slots for each day
	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(GET_PDA_SQL)) {

	        // Convert day name to day number
	        int d = 1;
	        if (day.equalsIgnoreCase("Tuesday")) {
	            d = 2;
	        } else if (day.equalsIgnoreCase("Wednesday")) {
	            d = 3;
	        } else if (day.equalsIgnoreCase("Thursday")) {
	            d = 4;
	        } else if (day.equalsIgnoreCase("Friday")) {
	            d = 5;
	        }

	        preparedStatement.setString(1, professor.getId());
	        preparedStatement.setInt(2, d);

	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                int timeslot = rs.getInt("timeslot"); // Slot number (0-15)
	                int isAvailable = rs.getInt("is_available"); // Availability (1 or 0)
	                timeslots[timeslot] = isAvailable == 1; // Store as true or false in timeslot array
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return timeslots; // Return the availability for the given day and professor
	}
	
	public Map<Integer, boolean[]> getAvailabilityForProfessor(String professorId) {
	    Map<Integer, boolean[]> availabilityMap = new HashMap<>();
	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(GET_AVAILABILITY_SQL)) {

	        preparedStatement.setString(1, professorId);
	        
	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                int day = rs.getInt("day");
	                int timeslot = rs.getInt("time");
	                boolean isAvailable = rs.getInt("is_available") == 1;

	                boolean[] timeslots = availabilityMap.computeIfAbsent(day, k -> new boolean[16]);
	                timeslots[timeslot] = isAvailable;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return availabilityMap;
	}
	
	public static String getTimeSlotDisplay(int slot) {
	    int hour = 10 + slot / 2; // Starts from 10 AM, assuming each slot is 30 minutes
	    int minute = (slot % 2) * 30; // Alternates between 00 and 30 minutes
	    return String.format("%02d:%02d", hour, minute); // Return formatted time as HH:MM
	}


	
	
}