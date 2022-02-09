package ecommercGesture.application.userQueriesCommandsEvents.events;

import ecommercGesture.domain.objects.Id;
import kernel.ApplicationEvent;

public class ModifyUserUserNameEvent implements ApplicationEvent{

    public final Id userId;
    
    public static ModifyUserUserNameEvent of(Id id) {
        return new ModifyUserUserNameEvent(id);
    }

    private ModifyUserUserNameEvent(Id userId) {
        this.userId = userId;
    }
	
}
