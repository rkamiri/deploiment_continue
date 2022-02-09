package ecommercGesture.application.userQueriesCommandsEvents.commands;

import kernel.Command;

@SuppressWarnings("all")
public final class CreateUser implements Command {
	
	public final String name;
	public final String lastName;
	public final String userName;
	public final String password;

    public CreateUser(String name,String lastName,String userName, String password) {
    	this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
}
