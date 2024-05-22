package ReservationModule;

public class Student extends User implements  Signup, Do_Reservation, Show_Reservation, Delete_Reservation{
    String department;
    String school;
    int year;

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


    public Student(String username, String password, String name, String id, String department, String school, int year){
        super(username, password, name, id);
        setDepartment(department);
        setYear(year);
        setSchool(school);
    }
}
