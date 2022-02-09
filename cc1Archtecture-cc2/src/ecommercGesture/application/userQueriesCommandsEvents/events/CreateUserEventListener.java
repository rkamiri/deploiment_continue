package ecommercGesture.application.userQueriesCommandsEvents.events;

import kernel.EventListener;

public class CreateUserEventListener implements EventListener<CreateUserEvent> {
	
    @Override
    public void listenTo(CreateUserEvent event) {
        System.out.println("listening CreateUserEvent.");
    }
}
