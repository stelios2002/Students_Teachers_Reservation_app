package ReservationModule.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ReservationModule.users.models.Student;
import ReservationModule.users.models.User;

public class StudentDao {
	private String jdbcURL = "jdbc:mysql://127.0.0.1:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	
	private static final String INSERT_USER_SQL = "INSERT INTO user (username, password, first_name, surname, role) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERT_STUDENT_SQL = "INSERT INTO student (username, department, school, year, id) VALUES (?, ?, ?, ?, ?)";
	private static final String LOGIN_STUDENT_SQL = "select * from student where username = ? and password = ? ";

	

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

	public void insertStudent(Student student) throws SQLException {
		System.out.println(INSERT_STUDENT_SQL);
		System.out.println(INSERT_USER_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL);
	            PreparedStatement studentStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
			userStatement.setString(1, student.getUsername());
            userStatement.setString(2, student.getPassword());
            userStatement.setString(3, student.getName());
            userStatement.setString(4, student.getSurname());
            userStatement.setInt(5, student.getRole());
            userStatement.executeUpdate();
            
            // Set parameters for student table
            studentStatement.setString(1, student.getUsername());
            studentStatement.setString(2, student.getDepartment());
            studentStatement.setString(3, student.getSchool());
            studentStatement.setInt(4, student.getYear());
            studentStatement.setString(5, student.getId());
            studentStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
	public boolean LoginTheStudent(Student student) throws SQLException {
		boolean status= false;
		System.out.println(LOGIN_STUDENT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_STUDENT_SQL)) {
			preparedStatement.setString(1, student.getUsername());
			preparedStatement.setString(2, student.getPassword());
			
			System.out.println(preparedStatement);
			preparedStatement.executeQuery();
			status= preparedStatement.executeQuery().next();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		return status;
	}

	public Student getStudent(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}
}
