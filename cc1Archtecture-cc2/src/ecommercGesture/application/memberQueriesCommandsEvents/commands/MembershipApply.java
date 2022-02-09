package ecommercGesture.application.memberQueriesCommandsEvents.commands;

import ecommercGesture.exposition.memberDTO.MembershipApplyDTO;
import kernel.Command;

@SuppressWarnings("all")
public class MembershipApply implements Command {
	
	public final MembershipApplyDTO membershipApplyDTO;
	
	public MembershipApply(MembershipApplyDTO membershipApplyDTO) {
		this.membershipApplyDTO = membershipApplyDTO;
	}

}
