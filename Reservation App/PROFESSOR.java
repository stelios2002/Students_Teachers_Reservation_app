public class PROFESSOR extends USER implements SINGUP, ACCEPT_RESERVATION, SHOW_RESERVATION, POSTPONE_RESERVATION{
    String department;
    String school;
    String specialty;

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


    public STUDENT(String username, String password, String name, String id, String department, String school, String specialty){
        super(username, password, name, id);
        setDepartment(department);
        setSchool(school);
        setSpecialty(specialty);
    }
}
