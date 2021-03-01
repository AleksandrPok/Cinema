package philharmonic.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import philharmonic.annotation.EmailValidation;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    private static final String EMAIL_REGEX = "\\w+@[a-z]+\\.[a-z]+";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}
