package ecommercGesture.application.userQueriesCommandsEvents.queries;

import ecommercGesture.domain.objects.Id;
import ecommercGesture.domain.objects.User;
import ecommercGesture.domain.services.UserService;
import ecommercGesture.exposition.userDTO.UserDTO;
import kernel.QueryHandler;

public class RetrieveUserByIdHandler implements QueryHandler<RetrieveUserById, UserDTO> {
	
    private final UserService userService;

    public RetrieveUserByIdHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO handle(RetrieveUserById query) {
    	Id userId = Id.of(query.userId);
    	User user = userService.getUserById(userId);
        UserDTO userResponseResult = UserDTO.of(user.getId().getId(), user.getName(), user.getLastName(), user.getUserName(), user.getPassword());
        return userResponseResult;
    }
}
