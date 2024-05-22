package ReservationModule;

public class Admin extends User implements ΙConfirm_Signup_S, ΙConfirm_Signup_P, ΙDelete_Reservation{

    public Admin(String username,String password,String name,String id){
        super(username,password,name,id);
    }
}
