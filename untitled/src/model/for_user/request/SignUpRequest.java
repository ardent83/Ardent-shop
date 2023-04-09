package model.for_user.request;

import model.for_user.UserType;

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
        return (new StringBuilder().append("\nRequest ID : ").append(super.idRequest).append("\n")
                .append("\nemail : ").append(email)
                .append("\nnumber : ").append(number)
                .append("\npassword : ").append( password).toString());
    }
}
