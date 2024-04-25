import java.time.LocalTime;
import java.time.LocalDateTime;

public class RESERVATION implements NOTIFY, PENDING, RESERVATION_PRIORITY{
    STUDENT student;
    PROFESSOR professor;
    LocalDate date;
    LocalTime time;
    int room;

    public String getStudent() {
        return student;
    }
    public String getProfessor() {
        return professor;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public int getRoom() {return room;}

    public void setStudent(STUDENT student){
        this.student = student;
    }
    public void setProfessor(PROFESSOR professor){
        this.professor = professor;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public void setTime(LocalTime time){
        this.time = time;
    }
    public void setRoom(int room){
        this.room = room;
    }

    public RESERVATION(STUDENT student, PROFESSOR professor, LocalDate date, LocalTime time, int room){
        setStudent(student);
        setProfessor(professor);
        setDate(date);
        setTime(time);
        setRoom(room);
    }
}