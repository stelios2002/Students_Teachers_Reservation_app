package ReservationModule.utils.models;

import java.time.LocalTime;

import ReservationModule.users.models.Professor;
import ReservationModule.users.models.Student;

import java.time.LocalDate;

public class Reservation{
    private Student student;
    private Professor professor;
    private LocalDate date;
    private LocalTime time;
    private int room;
    private final String id;

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

    public Reservation(Student student, Professor professor, LocalDate date, LocalTime time, int room, String id){
        setStudent(student);
        setProfessor(professor);
        setDate(date);
        setTime(time);
        setRoom(room);
        this.id = id;
    }
	public String getId() {
		return id;
	}
}
