package cz.kohnh.moro.users;

import ch.qos.logback.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

    @Component
    public class ResponseUserValidator  implements Validator {

        @Autowired
        private UserMapper userRepository;

        @Override
        public boolean supports(Class<?> clazz) {
            return ResponseUser.class.equals(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            ResponseUser userDTO = (ResponseUser) target;

            // Check for empty or null fields
            if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty()) {
                errors.rejectValue("username", "field.required", "Username is mandatory");
            }

            // Check for uniqueness of username
            User userByName = userRepository.selectByUsername(userDTO.getUsername());
            if (userDTO.getId() == null) {
                // new user
                if (userByName != null) {
                    errors.rejectValue("username", "field.duplicate", "Username must be unique");
                }

                if( StringUtil.isNullOrEmpty( userDTO.getPassword())){
                    errors.rejectValue("password", "field.required", "Password is mandatory");

                }

            } else {
                // update user
                if (userByName != null && !userByName.getId().equals(userDTO.getId())) {
                    errors.rejectValue("username", "field.duplicate", "Username must be unique");
                }
            }
        }
    }
