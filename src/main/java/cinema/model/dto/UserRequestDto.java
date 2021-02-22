package cinema.model.dto;

import cinema.annotation.EmailValidation;
import cinema.annotation.PasswordValidation;
import javax.validation.constraints.Size;

@PasswordValidation.List({
        @PasswordValidation(
                field = "password",
                fieldMatch = "repeatPassword"
        )
})
public class UserRequestDto {
    @EmailValidation
    private String email;
    @Size(min = 8)
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
