package ReservationModule;

public class Professor extends User implements ΙSignup, ΙAccept_Reservation, ΙShow_Reservation, ΙPostpone_Reservation{
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


    public Professor(String username, String password, String name, String id, String department, String school, String specialty){
        super(username, password, name, id);
        setDepartment(department);
        setSchool(school);
        setSpecialty(specialty);
    }
}
