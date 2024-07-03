package ReservationModule.users.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import ReservationModule.users.models.User;

public class UserDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "L1ok3y20";

	private static final String INSERT_USER_SQL = "INSERT INTO user" 
	+ "  (username, password, first_name, surname, role) VALUES (?, ?, ?, ?, ?); ";
	
	private static final String LOGIN_VALIDATION_PASSWORD_SQL = "SELECT role FROM user WHERE username = ? AND password = ?;";
	    
    private static final String LOGIN_VALIDATION_USERNAME_SQL = "SELECT role FROM user WHERE username = ? ;";

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

	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USER_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setInt(5, user.getRole());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	public int getRole(String username, String password) throws ServletException, IOException {

    	try (Connection connection = getConnection();
             PreparedStatement passwordPreparedStatement = connection.prepareStatement(LOGIN_VALIDATION_PASSWORD_SQL);
        		PreparedStatement usernamePreparedStatement = connection.prepareStatement(LOGIN_VALIDATION_USERNAME_SQL)) {
             
        	int role = -1;
        	
        	passwordPreparedStatement.setString(1, username);
        	passwordPreparedStatement.setString(2, password);
            
        	usernamePreparedStatement.setString(1, username);
        	ResultSet resultSet2 = usernamePreparedStatement.executeQuery();
        	if (resultSet2.next()) {
                role= 0;
            }
        	
            ResultSet resultSet = passwordPreparedStatement.executeQuery();

            if (resultSet.next()) {
               role = resultSet.getInt("role");
            }
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
            return -2;
        }
        
    }
}
