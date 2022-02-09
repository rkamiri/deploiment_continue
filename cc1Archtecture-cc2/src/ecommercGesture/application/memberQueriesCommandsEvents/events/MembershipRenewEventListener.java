package ecommercGesture.application.memberQueriesCommandsEvents.events;

import kernel.EventListener;

public class MembershipRenewEventListener implements EventListener<MembershipRenewEvent> {

    @Override
    public void listenTo(MembershipRenewEvent event) {
        System.out.println("listening MembershipRenewEvent.");
    }
}
