package ReservationModule.users.models;

public class Professor extends User{
    private String department;
    private String school;
    private String specialty;
    private final String id;

    public String getDepartment() {
        return department;
    }

    public String getSchool() {
        return school;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void setSchool(String school){
        this.school = school;
    }

    public void setSpecialty(String specialty){
        this.specialty = specialty;
    }


    public Professor(String username, String password, String name, String surname, String id, int role, String department, String school, String specialty){
        super(username, password, name, id, role);
        setDepartment(department);
        setSchool(school);
        setSpecialty(specialty);
        this.id = id;
    }

	public String getId() {
		return id;
	}
}
