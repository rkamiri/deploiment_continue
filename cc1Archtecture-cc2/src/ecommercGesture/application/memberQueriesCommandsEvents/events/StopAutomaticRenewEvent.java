package ecommercGesture.application.memberQueriesCommandsEvents.events;

import ecommercGesture.domain.objects.Id;
import kernel.ApplicationEvent;

public class StopAutomaticRenewEvent implements ApplicationEvent{

	public final Id memberId;
	
    public static StopAutomaticRenewEvent of(Id id) {
        return new StopAutomaticRenewEvent(id);
    }
	
	private StopAutomaticRenewEvent(Id memberId) {
		this.memberId = memberId;
	}
}
