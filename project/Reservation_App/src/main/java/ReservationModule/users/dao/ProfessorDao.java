package ReservationModule.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ReservationModule.users.models.Professor;

public class ProfessorDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "L1ok3y20";

	private static final String INSERT_USER_SQL = "INSERT INTO user (username, password, first_name, surname, role) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERT_PROFESSOR_SQL = "INSERT INTO professor (username, department, school, specialty, id) VALUES (?, ?, ?, ?, ?)";
	private static final String LOGIN_USER_SQL = "SELECT * FROM user WHERE username = ?;";
	private static final String LOGIN_PROFESSOR_SQL = "SELECT * FROM student WHERE username = ?;";

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

	public void insertProfessor(Professor professor) throws SQLException {
		System.out.println(INSERT_USER_SQL);
		System.out.println(INSERT_PROFESSOR_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL);
	            PreparedStatement professorStatement = connection.prepareStatement(INSERT_PROFESSOR_SQL)) {
			userStatement.setString(1, professor.getUsername());
            userStatement.setString(2, professor.getPassword());
            userStatement.setString(3, professor.getName());
            userStatement.setString(4, professor.getSurname());
            userStatement.setInt(5, professor.getRole());
            userStatement.executeUpdate();
            
            // Set parameters for student table
            professorStatement.setString(1, professor.getUsername());
            professorStatement.setString(2, professor.getDepartment());
            professorStatement.setString(3, professor.getSchool());
            professorStatement.setString(4, professor.getSpecialty());
            professorStatement.setString(5, professor.getId());
            professorStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Professor setProfessor(String username) {
		try (Connection connection = getConnection();
		         PreparedStatement userStatement = connection.prepareStatement(LOGIN_USER_SQL);
		         PreparedStatement professorStatement = connection.prepareStatement(LOGIN_PROFESSOR_SQL)) {
		        
		        userStatement.setString(1, username);
		        System.out.println(userStatement);
		        
		        try (ResultSet rsu = userStatement.executeQuery()) {
		            if (rsu.next()) {
		                professorStatement.setString(1, username);
		                System.out.println(professorStatement);
		                
		                try (ResultSet rsp = professorStatement.executeQuery()) {
		                    if (rsp.next()) {
		                        String uname = rsu.getString("username");
		                        String password = rsu.getString("password");
		                        String name = rsu.getString("first_name");
		                        String surname = rsu.getString("surname");
		                        int role = rsu.getInt("role");
		                        String dept = rsp.getString("department");
		                        String school = rsp.getString("school");
		                        String specialty = rsp.getString("specialty");
		                        String id = rsp.getString("id");
		                        
		                        return new Professor(uname, password, name, surname, role, dept, school, specialty, id);
		                    } else {
		                        // Handle case where no results are found in professor query
		                        System.out.println("No professor found with the provided username.");
		                        return null;
		                    }
		                }
		            } else {
		                // Handle case where no results are found in user query
		                System.out.println("No user found with the provided username.");
		                return null;
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;
		    }
	}

	public Professor getProfessor(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}
}
