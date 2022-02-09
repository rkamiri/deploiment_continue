package ecommercGesture.application.userQueriesCommandsEvents.events;

import ecommercGesture.domain.objects.Id;
import kernel.ApplicationEvent;

public class ModifyUserPasswordEvent implements ApplicationEvent {

    public final Id userId;
    
    public static ModifyUserPasswordEvent of(Id id) {
        return new ModifyUserPasswordEvent(id);
    }

    private ModifyUserPasswordEvent(Id userId) {
        this.userId = userId;
    }
}
