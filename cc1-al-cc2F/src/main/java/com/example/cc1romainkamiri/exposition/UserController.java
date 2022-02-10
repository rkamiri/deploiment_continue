package com.example.cc1romainkamiri.exposition;

import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.entity.User;
import com.example.cc1romainkamiri.domain.service.UserService;
import com.example.cc1romainkamiri.infrastructure.exceptions.NotFoundUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") int userId) throws NotFoundUserException {
        if(this.userService.getUser(Id.of(userId)).isPresent()){
            return ResponseEntity.status(200).body(this.userService.getUser(Id.of(userId)).get());
        } else {
            throw new NotFoundUserException(Id.of(userId));
        }
    }


    @GetMapping(value = "/deploy")
    public String getDeploy(){
        return "Agneugneu";
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody @Valid UserRequest request) {
        Id id = this.userService.addUser(new User(this.userService.getNextId(), request.firstname, request.lastname, request.password, request.email));
        return ResponseEntity.created(URI.create("/user/" + id.getId())).build();
    }

    @PutMapping
    public ResponseEntity<Void> editUser(@RequestBody @Valid UserRequest request) {
        Id id = this.userService.addUser(new User(this.userService.getNextId(), request.firstname, request.lastname, request.password, request.email));
        return ResponseEntity.created(URI.create("/user/" + id.getId())).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundUserException.class)
    public String handleNotFoundUserExceptions(
            NotFoundUserException ex) {

        return ex.getMessage();
    }
}
