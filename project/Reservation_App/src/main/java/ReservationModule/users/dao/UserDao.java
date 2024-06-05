package ReservationModule.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ReservationModule.users.models.Admin;
import ReservationModule.users.models.Professor;
import ReservationModule.users.models.Student;
import ReservationModule.users.models.User;

public class UserDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_USER_SQL = "INSERT INTO user" 
	+ "  (username, password, first_name, surname, role) VALUES (?, ?, ?, ?, ?); ";
	private static final String LOGIN_USER_SQL = "select * from user where username = ? and password = ? ";

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
	public User loginUser(String username, String password) {
	    User user = null;
	    String query = "SELECT user.username, user.password, user.first_name, user.surname, user.role, " +
	               "student.department, student.school, student.year, student.id, " +
	               "professor.department, professor.school, professor.specialty, professor.id " +
	               "FROM user " +
	               "LEFT JOIN student ON user.username = student.username " +
	               "LEFT JOIN professor ON user.username = professor.username " +
	               "WHERE user.username = ? AND user.password = ?";


	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setString(1, username);
	        preparedStatement.setString(2, password);
	        
	        System.out.println(preparedStatement); // Log the query
	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            if (rs.next()) {
	                String firstName = rs.getString("first_name");
	                String surname = rs.getString("surname");
	                int role = rs.getInt("role");
	                String dept = rs.getString("department"); // Retrieve dept column

	                if (role == 2) {
	                    String school = rs.getString("school"); // If other student columns are needed
	                    int year = rs.getInt("year"); // If other student columns are needed
	                    String id = rs.getString("id"); // If other student columns are needed
	                    user = new Student(username, password, firstName, surname, role, dept, school, year, id);
	                } else if (role == 3) {
	                    String specialty = rs.getString("specialty"); // If other professor columns are needed
	                    String school = rs.getString("school"); // If other professor columns are needed
	                    String id = rs.getString("id"); // If other professor columns are needed
	                    user = new Professor(username, password, firstName, surname, role, dept, school, specialty, id);
	                }else if (role == 1) {
	                    user = new Admin(username, password, firstName, surname, role);
	                
	                }else {
	                    // Handle other roles if necessary
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}

	public List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}
}
