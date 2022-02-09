package ecommercGesture.exposition.userDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserModifyPasswordDTO {

    @NotNull
    public int userId;

    @NotNull
    @NotBlank
    public String password;
	
}
