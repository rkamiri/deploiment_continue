package com.example.cc1romainkamiri.application.memberCQS.commands;

import com.example.cc1romainkamiri.kernel.Command;

public class RenewMembership implements Command {

	public final int userId;
	
	public RenewMembership(int userId) {
		this.userId = userId;
	}
}
