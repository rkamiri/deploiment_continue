package ecommercGesture.application.userQueriesCommandsEvents.events;

import kernel.EventListener;

public class ModifyUserPasswordEventListener implements EventListener<ModifyUserPasswordEvent> {

    @Override
    public void listenTo(ModifyUserPasswordEvent event) {
        System.out.println("listening ModifyUserPasswordEvent.");
    }
}
