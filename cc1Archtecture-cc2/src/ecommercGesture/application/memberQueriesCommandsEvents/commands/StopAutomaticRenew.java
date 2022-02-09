package ecommercGesture.application.memberQueriesCommandsEvents.commands;

import kernel.Command;

public class StopAutomaticRenew implements Command{

	public final int userId;
	
	public StopAutomaticRenew(int userId) {
		this.userId = userId;
	}
}
