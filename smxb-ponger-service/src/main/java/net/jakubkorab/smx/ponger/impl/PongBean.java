package net.jakubkorab.smx.ponger.impl;

import net.jakubkorab.smx.ponger.Pong;

public class PongBean implements Pong {

	@Override
	public String pong(String message) {
		return "Pong from service to [" + message + "]";
	}

}
