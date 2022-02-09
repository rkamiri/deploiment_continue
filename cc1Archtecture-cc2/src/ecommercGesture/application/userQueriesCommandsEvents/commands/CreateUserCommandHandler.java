package ecommercGesture.application.userQueriesCommandsEvents.commands;

import ecommercGesture.application.userQueriesCommandsEvents.events.CreateUserEvent;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.User;
import ecommercGesture.domain.services.UserService;
import ecommercGesture.exposition.userDTO.UserDTO;
import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;

public class CreateUserCommandHandler implements CommandHandler<CreateUser, UserDTO> {

    private final UserService userService;
    private final EventDispatcher<Event> eventDispatcher;
    
    public CreateUserCommandHandler(UserService userService, EventDispatcher<Event> eventDispatcher) {
        this.userService = userService;
        this.eventDispatcher = eventDispatcher;
    }

    public UserDTO handle(CreateUser createUser) {
        final Id userId = userService.getNextId();
        User user = userService.addUser(User.of(userId, createUser.name, createUser.lastName,createUser.userName, createUser.password));
        eventDispatcher.dispatch(CreateUserEvent.of(userId));
        UserDTO userResponseResult = UserDTO.of(user.getId().getId(), user.getName(), user.getLastName(), user.getUserName(), user.getPassword());
        return userResponseResult;
    }
}
