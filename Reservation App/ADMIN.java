public class ADMIN extends USER implements CONFIRM_SIGNUP_S, CONFIRM_SIGNUP_P, DELETE_RESERVATION{

    public ADMIN(String username,String password,String name,String id){
        super(username,password,name,id);
    }
}
