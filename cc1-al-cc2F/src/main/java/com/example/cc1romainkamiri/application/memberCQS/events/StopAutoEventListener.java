package com.example.cc1romainkamiri.application.memberCQS.events;


import com.example.cc1romainkamiri.kernel.EventListener;

public class StopAutoEventListener implements EventListener<StopAutoRenewEvent> {

    @Override
    public void listenTo(StopAutoRenewEvent event) {
        System.out.println("listening MembershipRenewEvent.");
    }
}
