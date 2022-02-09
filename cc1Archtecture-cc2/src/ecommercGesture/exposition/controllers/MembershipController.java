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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ecommercGesture.application.memberQueriesCommandsEvents.commands.MembershipApply;
import ecommercGesture.application.memberQueriesCommandsEvents.commands.MembershipRenew;
import ecommercGesture.application.memberQueriesCommandsEvents.commands.StopAutomaticRenew;
import ecommercGesture.application.memberQueriesCommandsEvents.queries.RetrieveMembershipById;
import ecommercGesture.application.memberQueriesCommandsEvents.queries.RetrieveMemberships;
import ecommercGesture.exposition.memberDTO.MembershipApplyDTO;
import ecommercGesture.exposition.memberDTO.MembershipDTO;
import ecommercGesture.exposition.memberDTO.MembershipsDTO;
import ecommercGesture.infrastructure.exception.InvalidMembershipDurationException;
import ecommercGesture.infrastructure.exception.InvalidUserException;
import ecommercGesture.infrastructure.exception.IsAlreadyMemberException;
import ecommercGesture.infrastructure.exception.IsCurrentlyMemberException;
import ecommercGesture.infrastructure.exception.IsNotMemberException;
import ecommercGesture.infrastructure.exception.NegativePriceException;
import ecommercGesture.infrastructure.exception.NoSuchBillingIformationException;
import ecommercGesture.infrastructure.exception.NotMemberException;
import ecommercGesture.infrastructure.exception.PaymentErrorException;
import ecommercGesture.infrastructure.exception.StopRenwCurrentlyNotMemberException;
import kernel.CommandBus;
import kernel.NoSuchEntityException;
import kernel.QueryBus;

@RestController
@RequestMapping("member")
public class MembershipController {
	
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public MembershipController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MembershipDTO> getById(@RequestParam(name = "id") int id) {
       final MembershipDTO memberResponseResult = queryBus.send(new RetrieveMembershipById(id));
       return ResponseEntity.ok(memberResponseResult);
    }

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MembershipsDTO> getAll() {
        final MembershipsDTO membersResponseResult = queryBus.send(new RetrieveMemberships());
        return ResponseEntity.ok(membersResponseResult);
    }
    
    @PostMapping(value ="/apply", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MembershipDTO> applyForMembership(@RequestBody @Valid MembershipApplyDTO request) {
    	final MembershipApply membershipApply = new MembershipApply(request);
    	final MembershipDTO membersResponseResult = commandBus.send(membershipApply);
        return ResponseEntity.ok(membersResponseResult);
    }
    
    @PostMapping(value ="/renew", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MembershipDTO> renewMembership(@RequestParam(name = "id") int userId) {
    	final MembershipRenew membershipRenew = new MembershipRenew(userId);
    	final MembershipDTO membersResponseResult = commandBus.send(membershipRenew);
        return ResponseEntity.ok(membersResponseResult);
    }
    
    @PostMapping(value ="/stopautomaticrenew", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MembershipDTO> stopAutomaticMembershipRenew(@RequestParam(name = "id") int userId) {
    	final StopAutomaticRenew stopAutomaticRenew = new StopAutomaticRenew(userId);
    	final MembershipDTO membersResponseResult = commandBus.send(stopAutomaticRenew);
        return ResponseEntity.ok(membersResponseResult);
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
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUserException.class)
    public String handleInvalidUserException(
    		InvalidUserException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IsAlreadyMemberException.class)
    public String handleIsAlreadyMemberException(
    		IsAlreadyMemberException ex) {
        return ex.getMessage();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IsCurrentlyMemberException.class)
    public String handleIsCurrentlyMemberException(
    		IsCurrentlyMemberException ex) {
        return ex.getMessage();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IsNotMemberException.class)
    public String handleIsNotMemberException(
    		IsNotMemberException ex) {
        return ex.getMessage();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchBillingIformationException.class)
    public String handleNoSuchBillingIformationException(
    		NoSuchBillingIformationException ex) {
        return ex.getMessage();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotMemberException.class)
    public String handleNotMemberException(
    		NotMemberException ex) {
        return ex.getMessage();
    }
    
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ExceptionHandler(PaymentErrorException.class)
    public String handlePaymentErrorException(
    		PaymentErrorException ex) {
        return ex.getMessage();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidMembershipDurationException.class)
    public String handleInvalidMembershipDurationException(
    		InvalidMembershipDurationException ex) {
        return ex.getMessage();
    }
    
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ExceptionHandler(NegativePriceException.class)
    public String handleNegativePriceException(
    		NegativePriceException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ExceptionHandler(StopRenwCurrentlyNotMemberException.class)
    public String handleStopRenwCurrentlyNotMemberException(
    		StopRenwCurrentlyNotMemberException ex) {
        return ex.getMessage();
    }
    
}
