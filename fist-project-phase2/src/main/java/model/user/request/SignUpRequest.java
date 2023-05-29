package model.user.request;

import model.user.UserType;

public class SignUpRequest extends Request {
    public SignUpRequest(String email, String number, String password) {
        this.email = email;
        this.number = number;
        this.password = password;
        this.userType = UserType.BUYER;
    }

    private final String email;
    private final String number;
    private final String password;
    private final UserType userType;

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

    public String toString() {
        return ("\nRequest ID : " + super.idRequest +
                "\nemail : " + email +
                "\nnumber : " + number +
                "\npassword : " + password);
    }
}
