package com.example.cc1romainkamiri.exposition;

import javax.validation.Valid;

import com.example.cc1romainkamiri.application.memberCQS.commands.ApplyToMembership;
import com.example.cc1romainkamiri.application.memberCQS.commands.RenewMembership;
import com.example.cc1romainkamiri.application.memberCQS.commands.StopAutoRenew;
import com.example.cc1romainkamiri.application.memberCQS.queries.FindMembershipById;
import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.exposition.membershipRequests.ApplyMembershipRequest;
import com.example.cc1romainkamiri.exposition.membershipRequests.MembershipRequest;
import com.example.cc1romainkamiri.infrastructure.exceptions.IsAlreadyMemberException;
import com.example.cc1romainkamiri.infrastructure.exceptions.NotFoundMembershipException;
import com.example.cc1romainkamiri.infrastructure.exceptions.UserNotMemberException;
import com.example.cc1romainkamiri.kernel.CommandBus;
import com.example.cc1romainkamiri.kernel.QueryBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("membership")
public class MembershipController {
	
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public MembershipController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MembershipRequest> getById(@PathVariable(value = "id") int id) throws NotFoundMembershipException {
       final MembershipRequest memberResponseResult = queryBus.send(new FindMembershipById(id));
       if(memberResponseResult == null){
           throw new NotFoundMembershipException(Id.of(id));
       }
       return ResponseEntity.ok(memberResponseResult);
    }
    
    @PostMapping(value ="/apply", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MembershipRequest> applyForMembership(@RequestBody @Valid ApplyMembershipRequest request) {
    	final ApplyToMembership membershipApply = new ApplyToMembership(request);
    	final MembershipRequest membersResponseResult = commandBus.send(membershipApply);
        return ResponseEntity.ok(membersResponseResult);
    }

    //used with cron task setup on server at a given time everyday
    @PostMapping(value ="/renewal/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MembershipRequest> renewMembership(@PathVariable(value = "id") int userId) {
    	final RenewMembership membershipRenew = new RenewMembership(userId);
    	final MembershipRequest membersResponseResult = commandBus.send(membershipRenew);
        return ResponseEntity.ok(membersResponseResult);
    }
    
    @PostMapping(value ="/stop-renewal/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MembershipRequest> stopAutomaticMembershipRenew(@PathVariable(value = "id") int userId) {
    	final StopAutoRenew stopAutomaticRenew = new StopAutoRenew(userId);
    	final MembershipRequest membersResponseResult = commandBus.send(stopAutomaticRenew);
        return ResponseEntity.ok(membersResponseResult);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundMembershipException.class)
    public String handleNotFoundMembershipExceptions(
            NotFoundMembershipException ex) {
        return ex.getMessage();
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotMemberException.class)
    public String handleUserNotMemberExceptions(
            UserNotMemberException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IsAlreadyMemberException.class)
    public String handleIsAlreadyMemberExceptions(
            IsAlreadyMemberException ex) {
        return ex.getMessage();
    }
}
