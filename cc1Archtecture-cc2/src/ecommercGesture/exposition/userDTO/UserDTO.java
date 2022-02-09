package ecommercGesture.exposition.userDTO;

@SuppressWarnings("all")
public class UserDTO {

    public int id;
    public String name;
    public String lastName;
    public String userName;
    public String password;
    
    public static UserDTO of(int id, String name, String lastName,  String userName, String password) {
    	return new UserDTO(id,name,lastName,userName,password);
    }

    private UserDTO(int id, String name, String lastName,  String userName, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName=" + userName + '\'' +
                ", password=" + password +
                '}';
    }
}
