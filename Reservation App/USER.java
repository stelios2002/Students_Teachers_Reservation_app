public abstract class USER implements LOGIN, LOGOUT {
    private String username;
    private String password;
    private String name;
    private String id;

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setID(String id){
        this.id = id;
    }

    public USER(String username, String password, String name, String id){
        setUsername(username);
        setPassword(password);
        setName(name);
        setID(id);
    }
}