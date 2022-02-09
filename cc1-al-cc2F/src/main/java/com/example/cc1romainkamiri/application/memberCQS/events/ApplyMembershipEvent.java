package com.example.cc1romainkamiri.application.memberCQS.events;


import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.kernel.ApplicationEvent;

public class ApplyMembershipEvent implements ApplicationEvent {
	
	public final Id userId;
	
    public static ApplyMembershipEvent of(Id id) {
        return new ApplyMembershipEvent(id);
    }
	
	private ApplyMembershipEvent(Id memberId) {
		this.userId = memberId;
	}
}
