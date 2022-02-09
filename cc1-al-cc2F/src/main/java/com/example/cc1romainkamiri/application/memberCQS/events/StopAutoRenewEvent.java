package com.example.cc1romainkamiri.application.memberCQS.events;


import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.kernel.ApplicationEvent;

public class StopAutoRenewEvent implements ApplicationEvent {

	public final Id userId;
	
    public static StopAutoRenewEvent of(Id id) {
        return new StopAutoRenewEvent(id);
    }
	
	private StopAutoRenewEvent(Id memberId) {
		this.userId = memberId;
	}
}
