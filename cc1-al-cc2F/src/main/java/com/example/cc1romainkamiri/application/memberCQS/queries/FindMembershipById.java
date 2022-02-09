package com.example.cc1romainkamiri.application.memberCQS.queries;


import com.example.cc1romainkamiri.kernel.Query;

public class FindMembershipById implements Query {
	public final int membershipId;
	
    public FindMembershipById(int memberId) {
        this.membershipId = memberId;
    }
}