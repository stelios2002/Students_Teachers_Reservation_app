package ReservationModule.users.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

public class UserDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "giorgos2002!";
	
	private static final String LOGIN_VALIDATION_PASSWORD_SQL = "SELECT role FROM user WHERE username = ? AND password = ?;";
    private static final String LOGIN_VALIDATION_USERNAME_SQL = "SELECT role FROM user WHERE username = ? ;";
    private static final String DELETE_USER_SQL = "DELETE FROM user WHERE username = ?;";

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
	
	public int login(String username, String password) throws ServletException, IOException {

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
	
	public void deleteUser(String id) {
		try (Connection connection = getConnection();
        		PreparedStatement usernamePreparedStatement = connection.prepareStatement(LOGIN_VALIDATION_USERNAME_SQL);
				PreparedStatement deleteStatement = connection.prepareStatement(DELETE_USER_SQL)) {
	            
	        	usernamePreparedStatement.setString(1, id);
	        	ResultSet resultSet = usernamePreparedStatement.executeQuery();
	        	int role = 0;
	            if (resultSet.next()) {
	               role = resultSet.getInt("role");
	            }
	            if (role == 2) {
	            	StudentDao studentDao = new StudentDao();
	            	studentDao.deleteStudent(id);
	            }
	            else if (role == 3) {
	            	ProfessorDao professorDao = new ProfessorDao();
	            	professorDao.deleteProfessor(id);
	            }
	            deleteStatement.execute();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	    }
}
