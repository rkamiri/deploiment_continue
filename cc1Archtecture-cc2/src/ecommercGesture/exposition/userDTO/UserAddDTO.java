package ecommercGesture.exposition.userDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserAddDTO {

    @NotNull
    @NotBlank
    public String name;
    
    @NotNull
    @NotBlank
    public String lastName;
    
    @NotNull
    @NotBlank
    public String userName;

    @NotNull
    @NotBlank
    public String password;
    
}
