package ReservationModule.users.models;

public class Student extends User{
    private String department;
    private String school;
    private int year;
    private final String id;
    
    public String getDepartment() {
        return department;
    }

    public String getSchool() {
        return school;
    }

    public int getYear() {
        return year;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void setSchool(String school){
        this.school = school;
    }

    public void setYear(int year){
        this.year = year;
    }


    public Student(String username, String password, String name, String surname, int role, String department, String school, int year, String id){
        super(username, password, name, surname, role);
        setDepartment(department);
        setYear(year);
        setSchool(school);
        this.id = id;
    }

	public String getId() {
		return id;
	}
}
