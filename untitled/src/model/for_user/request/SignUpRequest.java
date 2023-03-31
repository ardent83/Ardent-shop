package model.for_user.request;

import model.for_user.UserType;

public class SignUpRequest extends Request {
    public SignUpRequest(String email, String number, String password, UserType userType) {
        this.email = email;
        this.number = number;
        this.password = password;
        this.userType = userType;
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
}
