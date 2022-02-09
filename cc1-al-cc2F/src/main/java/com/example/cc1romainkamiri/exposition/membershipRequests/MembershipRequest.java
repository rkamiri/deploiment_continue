package com.example.cc1romainkamiri.exposition.membershipRequests;

import java.time.LocalDate;

public class MembershipRequest {
	
    public int id;
    public int userId;
    public double price;
    public LocalDate startMembership;
    public LocalDate endMembership;
    public boolean autoRenewal;
    
    public static MembershipRequest of(int id, int userId, double price, LocalDate startMembership, LocalDate endMembership, boolean autoRenewal) {
    	return new MembershipRequest(id, userId, price, startMembership, endMembership, autoRenewal);
    }
   

    private MembershipRequest(int id, int userId, double price, LocalDate startMembership, LocalDate endMembership, boolean autoRenewal) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.startMembership = startMembership;
        this.endMembership = endMembership;
    }
}
