package ecommercGesture.application.userQueriesCommandsEvents.events;

import kernel.EventListener;

public class ModifyUserUserNameEventListener implements EventListener<ModifyUserUserNameEvent> {

    @Override
    public void listenTo(ModifyUserUserNameEvent event) {
        System.out.println("listening ModifyUserUserNameEvent.");
    }
}
