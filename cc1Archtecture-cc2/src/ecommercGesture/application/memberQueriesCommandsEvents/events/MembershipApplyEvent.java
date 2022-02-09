package ecommercGesture.application.memberQueriesCommandsEvents.events;


import ecommercGesture.domain.objects.Id;
import kernel.ApplicationEvent;

public class MembershipApplyEvent implements ApplicationEvent {
	
	public final Id memberId;
	
    public static MembershipApplyEvent of(Id id) {
        return new MembershipApplyEvent(id);
    }
	
	private MembershipApplyEvent(Id memberId) {
		this.memberId = memberId;
	}
}
