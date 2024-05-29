package ReservationModule.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ReservationModule.users.models.Student;

public class StudentDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_STUDENT_SQL = "INSERT INTO users" 
	+ "  (username, password, name, surname, role) VALUES (?, ?, ?, ?, ?); " + "INSERT INTO students" 
	+ "  (department, school, year, id) VALUES (?, ?, ?, ?); ";

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

	public void insertUser(Student student) throws SQLException {
		System.out.println(INSERT_STUDENT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
			preparedStatement.setString(1, student.getUsername());
			preparedStatement.setString(2, student.getPassword());
			preparedStatement.setString(3, student.getName());
			preparedStatement.setString(4, student.getSurname());
			preparedStatement.setInt(5, student.getRole());
			preparedStatement.setString(6, student.getDepartment());
			preparedStatement.setString(7, student.getSchool());
			preparedStatement.setInt(8, student.getYear());
			preparedStatement.setString(9, student.getId());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
}
