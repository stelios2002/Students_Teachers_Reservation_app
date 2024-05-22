package ReservationModule;

public class Admin extends User implements Confirm_Signup_S, Confirm_Signup_P, Delete_Reservation{

    public Admin(String username,String password,String name,String id){
        super(username,password,name,id);
    }
}
