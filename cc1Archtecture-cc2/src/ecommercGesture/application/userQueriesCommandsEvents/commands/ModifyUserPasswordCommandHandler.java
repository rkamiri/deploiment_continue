package ecommercGesture.application.userQueriesCommandsEvents.commands;

import ecommercGesture.application.userQueriesCommandsEvents.events.ModifyUserPasswordEvent;
import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.User;
import ecommercGesture.domain.services.UserService;
import ecommercGesture.exposition.userDTO.UserDTO;
import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;

public class ModifyUserPasswordCommandHandler implements CommandHandler<ModifyUserPassword, UserDTO> {
	
    private final UserService userService;
    private final EventDispatcher<Event> eventDispatcher;
    
    public ModifyUserPasswordCommandHandler(UserService userService, EventDispatcher<Event> eventDispatcher) {
        this.userService = userService;
        this.eventDispatcher = eventDispatcher;
    }

    public UserDTO handle(ModifyUserPassword command) {
    	Id userId = Id.of(command.userId);
    	User user = userService.changePassword(userId, command.password);
    	eventDispatcher.dispatch(ModifyUserPasswordEvent.of(userId));
        UserDTO userResponseResult = UserDTO.of(user.getId().getId(), user.getName(), user.getLastName(), user.getUserName(), user.getPassword());
    	return userResponseResult;
    }

}
