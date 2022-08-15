package com.example.springsecuritydemo.model.validator;

import com.example.springsecuritydemo.model.Post;
import com.example.springsecuritydemo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PostValidator implements Consumer<Post> {
    private final Validator validator;

    public void accept(List< Post > users){
      users.forEach(this::accept);
    }

    @Override
    public void accept(Post post) {
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        if(!violations.isEmpty()){
            String violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(". "));
            throw new IllegalArgumentException(violationMessages);
            // Please build the corresponding exception : Forbidden, BadRequest etc.
        }
    }
}
