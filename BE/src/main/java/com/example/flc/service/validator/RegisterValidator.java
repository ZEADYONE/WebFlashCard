package com.example.flc.service.validator;

import com.example.flc.domain.DTO.SignUp;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegisterValidator implements ConstraintValidator<RegisterCheck, SignUp> {

    @Override
    public boolean isValid(SignUp user, ConstraintValidatorContext context) {
        boolean valid = true;

        if (!user.getPassWord().equals(user.getRewritePassWord())) {
            context.buildConstraintViolationWithTemplate("Password không khớp")
                    .addPropertyNode("rewritePassWord")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();

            valid = false;
        }
        return valid;
    }

}
