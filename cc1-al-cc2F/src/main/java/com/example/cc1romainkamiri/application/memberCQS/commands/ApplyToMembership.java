package com.example.cc1romainkamiri.application.memberCQS.commands;


import com.example.cc1romainkamiri.exposition.membershipRequests.ApplyMembershipRequest;
import com.example.cc1romainkamiri.kernel.Command;

public class ApplyToMembership implements Command {
	
	public final ApplyMembershipRequest membershipApplyRequest;
	
	public ApplyToMembership(ApplyMembershipRequest request) {
		this.membershipApplyRequest = request;
	}

}
