package ecommercGesture.application.userQueriesCommandsEvents.commands;

import kernel.Command;

@SuppressWarnings("all")
public class ModifyUserUserName implements Command {
	
    public final int userId;
    public final String userName;

    public ModifyUserUserName(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
