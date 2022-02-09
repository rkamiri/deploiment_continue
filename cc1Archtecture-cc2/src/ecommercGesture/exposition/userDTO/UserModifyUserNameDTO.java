package ecommercGesture.exposition.userDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserModifyUserNameDTO {

    @NotNull
    public int userId;

    @NotNull
    @NotBlank
    public String userName;
	
}