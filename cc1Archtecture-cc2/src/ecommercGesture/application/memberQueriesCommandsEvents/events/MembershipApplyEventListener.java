package ecommercGesture.application.memberQueriesCommandsEvents.events;

import kernel.EventListener;

public class MembershipApplyEventListener implements EventListener<MembershipApplyEvent> {
	
    @Override
    public void listenTo(MembershipApplyEvent event) {
        System.out.println("listening MembershipApplyEvent.");
    }
}
