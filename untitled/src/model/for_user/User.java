package model.for_user;
public abstract class User {
    public User(String id, String email, String number, String password, UserType userType) {
        this.id = id;
        this.email = email;
        this.number = number;
        this.password = password;
        this.userType = userType;
    }

    private String id;
    private String email;
    private String number;
    private String password;
    private UserType userType;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }
}
