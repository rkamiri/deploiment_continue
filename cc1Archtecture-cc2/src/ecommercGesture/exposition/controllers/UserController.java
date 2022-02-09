package ecommercGesture.exposition.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ecommercGesture.application.userQueriesCommandsEvents.commands.CreateUser;
import ecommercGesture.application.userQueriesCommandsEvents.commands.ModifyUserPassword;
import ecommercGesture.application.userQueriesCommandsEvents.commands.ModifyUserUserName;
import ecommercGesture.application.userQueriesCommandsEvents.queries.RetrieveUserById;
import ecommercGesture.application.userQueriesCommandsEvents.queries.RetrieveUsers;
import ecommercGesture.exposition.userDTO.UserAddDTO;
import ecommercGesture.exposition.userDTO.UserDTO;
import ecommercGesture.exposition.userDTO.UserModifyPasswordDTO;
import ecommercGesture.exposition.userDTO.UserModifyUserNameDTO;
import ecommercGesture.exposition.userDTO.UsersDTO;
import kernel.CommandBus;
import kernel.NoSuchEntityException;
import kernel.QueryBus;

@RestController
@RequestMapping("user")
public class UserController {
	
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public UserController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> getById(@RequestParam(name = "id") int id) {
        final UserDTO userResponseResult = queryBus.send(new RetrieveUserById(id));
        return ResponseEntity.ok(userResponseResult);
    }

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersDTO> getAll() {
        final UsersDTO usersResponseResult = queryBus.send(new RetrieveUsers());
        return ResponseEntity.ok(usersResponseResult);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserAddDTO request) {
    	final CreateUser createUser = new CreateUser(request.name, request.lastName, request.userName, request.password);
        final UserDTO userResponseResult = commandBus.send(createUser);
        return ResponseEntity.ok(userResponseResult);
    }
    
    @PatchMapping(value = "/username", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changeUserName(@RequestBody @Valid UserModifyUserNameDTO request){
        final ModifyUserUserName modifyUserUserName = new ModifyUserUserName(request.userId, request.userName);
        final UserDTO userResponseResult = commandBus.send(modifyUserUserName);
        return ResponseEntity.ok(userResponseResult);
    }
    
    @PatchMapping(value = "/userpassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changeUserPassword(@RequestBody @Valid UserModifyPasswordDTO request){
    	final ModifyUserPassword modifyUserPassword = new ModifyUserPassword(request.userId, request.password);
        final UserDTO userResponseResult = commandBus.send(modifyUserPassword);
        return ResponseEntity.ok(userResponseResult);
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
    @ExceptionHandler(NoSuchEntityException.class)
    public String handleEntityExceptions(
    		NoSuchEntityException ex) {
        return ex.getMessage();
    }

}
