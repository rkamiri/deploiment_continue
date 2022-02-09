package ecommercGesture.application.memberQueriesCommandsEvents.events;

import ecommercGesture.domain.objects.Id;
import kernel.ApplicationEvent;

public class MembershipRenewEvent implements ApplicationEvent{

	public final Id memberId;
	
    public static MembershipRenewEvent of(Id id) {
        return new MembershipRenewEvent(id);
    }
	
	private MembershipRenewEvent(Id memberId) {
		this.memberId = memberId;
	}
}
