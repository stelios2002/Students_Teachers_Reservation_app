public class STUDENT extends USER implements  SIGNUP, DO_RESERVATION, SHOW_RESERVATION, DELETE_RESERVATION{
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


    public STUDENT(String username, String password, String name, String id, String department, String school, int year){
        super(username, password, name, id);
        setDepartment(department);
        setYear(year);
        setSchool(school);
    }
}
