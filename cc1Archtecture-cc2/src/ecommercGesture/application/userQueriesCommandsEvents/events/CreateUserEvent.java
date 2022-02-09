package ecommercGesture.application.userQueriesCommandsEvents.events;

import ecommercGesture.domain.objects.Id;
import kernel.ApplicationEvent;

public class CreateUserEvent implements ApplicationEvent {
	
    private final Id userId;
    
    public static CreateUserEvent of(Id id) {
        return new CreateUserEvent(id);
    }

    private CreateUserEvent(Id userId) {
        this.userId = userId;
    }
}
