package ReservationModule.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ReservationModule.users.models.Professor;

public class ProfessorDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_PROFESSOR_SQL = "INSERT INTO users" 
	+ "  (username, password, name, surname, role) VALUES (?, ?, ?, ?, ?); " + "INSERT INTO professors" 
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

	public void insertUser(Professor professor) throws SQLException {
		System.out.println(INSERT_PROFESSOR_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFESSOR_SQL)) {
			preparedStatement.setString(1, professor.getUsername());
			preparedStatement.setString(2, professor.getPassword());
			preparedStatement.setString(3, professor.getName());
			preparedStatement.setString(4, professor.getSurname());
			preparedStatement.setInt(5, professor.getRole());
			preparedStatement.setString(6, professor.getDepartment());
			preparedStatement.setString(7, professor.getSchool());
			preparedStatement.setString(8, professor.getSpecialty());
			preparedStatement.setString(9, professor.getId());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
}
