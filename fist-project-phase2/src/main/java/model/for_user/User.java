package model.for_user;
public abstract class User {
    public User(String email, String number, String password, UserType userType) {
        if(userType.equals(UserType.BUYER)){
            this.idUser = Integer.toHexString(countForId+43681);
        } else {
            this.idUser = "admin";
        }
        this.email = email;
        this.number = number;
        this.password = password;
        this.userType = userType;
        countForId++;
    }
    private static int countForId;
    protected final String idUser;
    protected String email;
    protected String number;
    protected String password;
    protected final UserType userType;

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
