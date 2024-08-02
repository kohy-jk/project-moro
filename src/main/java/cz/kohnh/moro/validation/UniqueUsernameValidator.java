package cz.kohnh.moro.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.kohnh.moro.users.UserMapper;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userMapper.selectByUsername(username) == null;
    }
}
