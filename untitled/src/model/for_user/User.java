package model.for_user;
public abstract class User {
    public User(String email, String number, String password, UserType userType) {
        this.idUser = Integer.toHexString(countForId+43681);
        this.email = email;
        this.number = number;
        this.password = password;
        this.userType = userType;
    }
    private static int countForId;
    private final String idUser;
    protected String email;
    protected String number;
    protected String password;
    private UserType userType;

    public String getId() {
        return idUser;
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
    static {
        countForId = 0;
    }
}
