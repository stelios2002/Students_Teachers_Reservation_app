package ReservationModule.utils.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ReservationModule.users.models.Professor;

public class AvailabilityDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "L1ok3y20";

	private static final String INSERT_PDA_SQL = "INSERT INTO availability (profid, time, day, is_available) VALUES (?, ?, ?, ?)"; //Prof,Day,Avail
	private static final String IS_REGISTERED_PDA_SQL = "SELECT * FROM availability WHERE profid=? AND day=?"; //Prof,Day,Avail
    private static final String EDIT_PDA_SQL = "UPDATE availability SET is_available = ? WHERE profid = ? AND day=? AND time = ?;"; //Prof,Day,Avail
	
	
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
			if(day == "Tuesday") {
				d = 2;
			} else if (day == "Wednesday") {
				d = 3;
			} else if (day == "Thursday") {
				d = 4;
			} else if (day == "Friday") {
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
				if(day == "Tuesday") {
					d = 2;
				} else if (day == "Wednesday") {
					d = 3;
				} else if (day == "Thursday") {
					d = 4;
				} else if (day == "Friday") {
					d = 5;
				}
				preparedStatement.setString(2, professor.getId());
				preparedStatement.setInt(3, d);
				preparedStatement.setInt(4, i);
				System.out.println(preparedStatement);
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
				if(day == "Tuesday") {
					d = 2;
				} else if (day == "Wednesday") {
					d = 3;
				} else if (day == "Thursday") {
					d = 4;
				} else if (day == "Friday") {
					d = 5;
				}
				preparedStatement.setString(1, professor.getId());
				preparedStatement.setInt(3, d);
				preparedStatement.setInt(2, i);
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
