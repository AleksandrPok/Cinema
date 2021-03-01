package philharmonic.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import philharmonic.annotation.PasswordValidation;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(PasswordValidation constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object user, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(user).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(user).getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        }
        return fieldMatchValue == null;
    }
}
