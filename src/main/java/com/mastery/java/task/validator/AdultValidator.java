package com.mastery.java.task.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {
    @Override
    public void initialize(Adult constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return ChronoUnit.YEARS.between(localDate, LocalDate.now()) > 18;
    }
}
