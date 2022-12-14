package com.example.springsecuritydemo.model.validator;

import com.example.springsecuritydemo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserValidator implements Consumer<User> {
    private final Validator validator;

    public void accept(List<User> users){
        users.forEach(this::accept);
    }

    @Override
    public void accept(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if(!violations.isEmpty()){
            String violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new IllegalArgumentException(violationMessages);
            // Please build the corresponding exception : Forbidden, BadRequest etc.
        }
    }
}
