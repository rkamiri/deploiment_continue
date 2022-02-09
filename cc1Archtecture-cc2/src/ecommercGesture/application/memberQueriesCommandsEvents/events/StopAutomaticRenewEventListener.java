package ecommercGesture.application.memberQueriesCommandsEvents.events;

import kernel.EventListener;

public class StopAutomaticRenewEventListener implements EventListener<StopAutomaticRenewEvent> {

    @Override
    public void listenTo(StopAutomaticRenewEvent event) {
        System.out.println("listening MembershipRenewEvent.");
    }
}
