package com.example.cc1romainkamiri.application.memberCQS.commands;


import com.example.cc1romainkamiri.kernel.Command;

public class StopAutoRenew implements Command {

	public final int userId;
	
	public StopAutoRenew(int userId) {
		this.userId = userId;
	}
}
