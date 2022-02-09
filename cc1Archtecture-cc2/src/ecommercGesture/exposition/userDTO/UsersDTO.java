package ecommercGesture.exposition.userDTO;

import java.util.List;

@SuppressWarnings("all")
public class UsersDTO {
    public final List<UserDTO> users;

    public static UsersDTO of(List<UserDTO> users) {
    	return new UsersDTO(users);
    }
    
    private UsersDTO(List<UserDTO> users) {
        this.users = users;
    }
}
