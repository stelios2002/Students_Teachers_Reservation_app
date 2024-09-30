package ReservationModule.users.models;

public abstract class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private final int role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getName() {
        return name;
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

    public User(String username, String password, String name, String surname, int role){
        setUsername(username);
        setPassword(password);
        setName(name);
        setSurname(surname);
        this.role = role;
    }

	public int getRole() {
		return role;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}