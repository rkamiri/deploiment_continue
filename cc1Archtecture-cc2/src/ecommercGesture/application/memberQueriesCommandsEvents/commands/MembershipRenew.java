package ecommercGesture.application.memberQueriesCommandsEvents.commands;

import kernel.Command;

public class MembershipRenew implements Command {

	public final int userId;
	
	public MembershipRenew(int userId) {
		this.userId = userId;
	}
}
