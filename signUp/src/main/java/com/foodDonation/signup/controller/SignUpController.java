package com.foodDonation.signup.controller;

import com.foodDonation.signup.data.UserData;
import com.foodDonation.signup.service.RepositoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class SignUpController {

    private RepositoryService repositoryService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid UserData userProfile){           //@valid anotation present in the javax.valid
        repositoryService.save(userProfile);
        return ResponseEntity.ok("User signed up successfully");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->                     //notnull anotation was present in the UserData class
                errors.put(error.getField(), error.getDefaultMessage()));           //for each missing field, error message will obtain from the notnull anotation message that is thrown
        return ResponseEntity.badRequest().body(errors);
    }

}
