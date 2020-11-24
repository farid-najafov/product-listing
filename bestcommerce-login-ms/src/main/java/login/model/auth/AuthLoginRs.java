package login.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthLoginRs {

    private int code;
    private String message;

    public static AuthLoginRs Ok() {
        return new AuthLoginRs(1, "authentication is successful!");
    }

    public static AuthLoginRs Fail() {
        return new AuthLoginRs(-1, "unsuccessful authentication attempt");
    }
}
