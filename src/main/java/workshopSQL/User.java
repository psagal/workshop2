package pl.coderslab.mysql.workshop2;

public class User {
    private int id;
    private String userName;
    private String password;
    private String email;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.id = 0;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "[id=" + id + ", userName='" + userName + "', email='" + email + "']";
    }
}
