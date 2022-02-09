package ecommercGesture.application.memberQueriesCommandsEvents.queries;

import kernel.Query;

public class RetrieveMembershipById implements Query {
	public final int memberId;
	
    public RetrieveMembershipById(int memberId) {
        this.memberId = memberId;
    }
}