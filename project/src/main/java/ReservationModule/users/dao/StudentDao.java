package ReservationModule.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ReservationModule.users.models.Student;
import ReservationModule.utils.dao.ReservationDao;

public class StudentDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/reservationdb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "giorgos2002!";
	
	private static final String INSERT_USER_SQL = "INSERT INTO user (username, password, first_name, surname, role) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERT_STUDENT_SQL = "INSERT INTO student (username, department, school, year, id) VALUES (?, ?, ?, ?, ?)";
	private static final String LOGIN_USER_SQL = "SELECT * FROM user WHERE username = ?;";
	private static final String LOGIN_STUDENT_SQL = "SELECT * FROM student WHERE username = ?;";
	private static final String ID_STUDENT_SQL = "SELECT * FROM student WHERE id = ?;";
    private static final String DELETE_STUDENT_SQL = "DELETE FROM student WHERE id = ?;";
    private static final String GET_USERS_SQL = "SELECT * FROM user WHERE role = 2;";

	protected Connection getConnection() {
        Connection connection = null;
        try {
        	 System.out.println("Loading driver class...");
             Class.forName("com.mysql.cj.jdbc.Driver");
             // Print debug statement
             System.out.println("Driver loaded successfully!");

             connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             // Print debug statement
             System.out.println("Connection established successfully!");
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class Not Found Exception: " + e.getMessage());
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
			e.printStackTrace();
		}
	}
	
	public ArrayList<Student> getStudents() throws SQLException{
		try (Connection connection = getConnection();
		    PreparedStatement userStatement = connection.prepareStatement(GET_USERS_SQL)){
			ArrayList<Student> students = new ArrayList<Student>();
			try (ResultSet rsu = userStatement.executeQuery()) { 
				while (rsu.next()) {
					students.add(getStudent(rsu.getString("username")));
				}
				return students;
			}
		}
	}
	
	public Student getStudent(String username) {
		try (Connection connection = getConnection();
		         PreparedStatement userStatement = connection.prepareStatement(LOGIN_USER_SQL);
		         PreparedStatement studentStatement = connection.prepareStatement(LOGIN_STUDENT_SQL)) {
		        
		        userStatement.setString(1, username);
		        System.out.println(userStatement);
		        
		        try (ResultSet rsu = userStatement.executeQuery()) {
		            if (rsu.next()) {
		                studentStatement.setString(1, username);
		                System.out.println(studentStatement);
		                
		                try (ResultSet rss = studentStatement.executeQuery()) {
		                    if (rss.next()) {
		                        String uname = rsu.getString("username");
		                        String password = rsu.getString("password");
		                        String name = rsu.getString("first_name");
		                        String surname = rsu.getString("surname");
		                        int role = rsu.getInt("role");
		                        String dept = rss.getString("department");
		                        String school = rss.getString("school");
		                        int year = rss.getInt("year");
		                        String id = rss.getString("id");
		                        
		                        return new Student(uname, password, name, surname, role, dept, school, year, id);
		                    } else {
		                        // Handle case where no results are found in student query
		                        System.out.println("No student found with the provided username.");
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
	
	public Student getStudentByID(String id) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement userStatement = connection.prepareStatement(LOGIN_USER_SQL);
		         PreparedStatement studentStatement = connection.prepareStatement(ID_STUDENT_SQL)) {
			studentStatement.setString(1, id);
		    try (ResultSet rss = studentStatement.executeQuery()){
		    	if (rss.next()) {
		    		userStatement.setString(1, rss.getString("username"));
		    		try (ResultSet rsu = userStatement.executeQuery()){
		    			if (rsu.next()) {
		    				String uname = rsu.getString("username");
	                        String password = rsu.getString("password");
	                        String name = rsu.getString("first_name");
	                        String surname = rsu.getString("surname");
	                        int role = rsu.getInt("role");
	                        String dept = rss.getString("department");
	                        String school = rss.getString("school");
	                        int year = rss.getInt("year");
	                        
	                        return new Student(uname, password, name, surname, role, dept, school, year, id);
	                    } else {
		    				System.out.println("No Student found with the provided id.");
	                        return null;
		    			}
		    		}
               } else {
                   // Handle case where no results are found in professor query
                   System.out.println("No user found with the provided id.");
                   return null;
               }
		    }
		}
	}

	public void deleteStudent(String id) throws SQLException {
		try (Connection connection = getConnection();
        		PreparedStatement deletePreparedStatement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
			ReservationDao reservationDao = new ReservationDao();
			reservationDao.deleteReservationsOfStudent(id);
			deletePreparedStatement.setString(1, id);
			deletePreparedStatement.execute();
		}		
	}
}
