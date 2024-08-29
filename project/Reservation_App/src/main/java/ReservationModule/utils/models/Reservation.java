package ReservationModule.utils.models;

import java.time.LocalTime;
import java.time.LocalDate;

public class Reservation{
    private String student;
    private String professor;
    private LocalDate date;
    private LocalTime time;
    private int room;
    private final String id;
    private boolean Accepted;

    public String getStudent() {
        return student;
    }
    public String getProfessor() {
        return professor;
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
    public int getRoom() {
    	return room;
    	}
    public void setStudent(String student){
        this.student = student;
    }
    public void setProfessor(String professor){
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

    public Reservation(String student, String professor, LocalDate date, LocalTime time, int room, String id, boolean accepted){
        setStudent(student);
        setProfessor(professor);
        setDate(date);
        setTime(time);
        setRoom(room);
        setAccepted(accepted);
        this.id = id;
    }
	public String getId() {
		return id;
	}
	public boolean isAccepted() {
		return Accepted;
	}
	public void setAccepted(boolean accepted) {
		Accepted = accepted;
	}
}
