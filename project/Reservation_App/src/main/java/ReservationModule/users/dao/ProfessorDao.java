package ReservationModule.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ReservationModule.users.models.Professor;
import ReservationModule.utils.dao.ReservationDao;

public class ProfessorDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	private static final String INSERT_USER_SQL = "INSERT INTO user (username, password, first_name, surname, role) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERT_PROFESSOR_SQL = "INSERT INTO professor (username, department, school, specialty, id) VALUES (?, ?, ?, ?, ?)";
	private static final String LOGIN_USER_SQL = "SELECT * FROM user WHERE username = ?;";
	private static final String LOGIN_PROFESSOR_SQL = "SELECT * FROM professor WHERE username = ?;";
	private static final String ID_PROFESSOR_SQL = "SELECT * FROM professor WHERE id = ?;";
    private static final String DELETE_PROFESSOR_SQL = "DELETE FROM professor WHERE id = ?;";
    private static final String GET_USERS_SQL ="SELECT * FROM user WHERE role = 3;";
    

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
	public ArrayList<Professor> getProfessors() throws SQLException {
		
		try (Connection connection = getConnection();
		    PreparedStatement userStatement = connection.prepareStatement(GET_USERS_SQL)){
			ArrayList<Professor> professors = new ArrayList<Professor>();
			try (ResultSet rsu = userStatement.executeQuery()) { 
				while (rsu.next()) {
					professors.add(getProfessor(rsu.getString("username")));
						
				}
				return professors;
			}
			
		}
		         
		
	}

	public Professor getProfessor(String username) {
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
	
	public Professor getProfessorByID(String id) throws SQLException {
		try (Connection connection = getConnection();
		         PreparedStatement userStatement = connection.prepareStatement(LOGIN_USER_SQL);
		         PreparedStatement professorStatement = connection.prepareStatement(ID_PROFESSOR_SQL)) {
		    professorStatement.setString(1, id);
		    try (ResultSet rsp = professorStatement.executeQuery()){
		    	if (rsp.next()) {
		    		userStatement.setString(1, rsp.getString("username"));
		    		try (ResultSet rsu = userStatement.executeQuery()){
		    			if (rsu.next()) {
		                    String uname = rsu.getString("username");
		                    String password = rsu.getString("password");
		                    String name = rsu.getString("first_name");
		                    String surname = rsu.getString("surname");
		                    int role = rsu.getInt("role");
		                    String dept = rsp.getString("department");
		                    String school = rsp.getString("school");
		                    String specialty = rsp.getString("specialty");
		                    return new Professor(uname, password, name, surname, role, dept, school, specialty, id);
		    			} else {
		    				System.out.println("No professor found with the provided username.");
	                        return null;
		    			}
		    		}
                } else {
                    // Handle case where no results are found in professor query
                    System.out.println("No professor found with the provided username.");
                    return null;
                }
		    }
		}
	}

	public void deleteProfessor(String id) throws SQLException {
		try (Connection connection = getConnection();
        		PreparedStatement deletePreparedStatement = connection.prepareStatement(DELETE_PROFESSOR_SQL)) {
			ReservationDao reservationDao = new ReservationDao();
			reservationDao.deleteReservationsOfProfessor(id);
			deletePreparedStatement.setString(1, id);
			deletePreparedStatement.execute();
		}
	}
}
