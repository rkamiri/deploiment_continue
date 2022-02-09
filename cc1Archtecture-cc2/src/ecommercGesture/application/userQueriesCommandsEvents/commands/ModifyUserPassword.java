package ecommercGesture.application.userQueriesCommandsEvents.commands;

import kernel.Command;

@SuppressWarnings("all")
public final class ModifyUserPassword implements Command {

    public final int userId;
    public final String password;

    public ModifyUserPassword(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }
	
}
