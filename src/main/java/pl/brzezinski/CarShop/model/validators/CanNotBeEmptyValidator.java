package pl.brzezinski.CarShop.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CanNotBeEmptyValidator implements ConstraintValidator<CanNotBeEmpty, Object> {

    @Override
    public void initialize(CanNotBeEmpty constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        return obj != null;
    }
}
