package ReservationModule;

import java.time.LocalTime;
import java.time.LocalDate;

public class Reservation implements Notify, Pending, Reservation_Priority{
    Student student;
    Professor professor;
    LocalDate date;
    LocalTime time;
    int room;

    public Student getStudent() {
        return student;
    }
    public Professor getProfessor() {
        return professor;
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
    public int getRoom() {return room;}

    public void setStudent(Student student){
        this.student = student;
    }
    public void setProfessor(Professor professor){
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

    public Reservation(Student student, Professor professor, LocalDate date, LocalTime time, int room){
        setStudent(student);
        setProfessor(professor);
        setDate(date);
        setTime(time);
        setRoom(room);
    }
}
