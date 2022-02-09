package ecommercGesture.application.userQueriesCommandsEvents.queries;

import java.util.List;
import java.util.stream.Collectors;

import ecommercGesture.domain.objects.User;
import ecommercGesture.domain.services.UserService;
import ecommercGesture.exposition.userDTO.UserDTO;
import ecommercGesture.exposition.userDTO.UsersDTO;
import kernel.QueryHandler;

public class RetrieveUsersHandler implements QueryHandler<RetrieveUsers, UsersDTO> {

    private final UserService userService;

    public RetrieveUsersHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UsersDTO handle(RetrieveUsers query) {
        List<User> users = userService.getAll();
        UsersDTO usersResponseResult = UsersDTO.of(users.stream().map(user -> UserDTO.of(user.getId().getId(), user.getName(), user.getLastName(), user.getUserName(), user.getPassword())).collect(Collectors.toList()));
        return usersResponseResult;
    }
}
