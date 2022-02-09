package com.example.cc1romainkamiri.application.memberCQS.events;


import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.kernel.ApplicationEvent;

public class RenewMembershipEvent implements ApplicationEvent {

	public final Id userId;
	
    public static RenewMembershipEvent of(Id id) {
        return new RenewMembershipEvent(id);
    }
	
	private RenewMembershipEvent(Id memberId) {
		this.userId = memberId;
	}
}
