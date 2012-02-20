package com.fusesource.examples.ponger.impl;

import com.fusesource.examples.ponger.Pong;

public class PongBean implements Pong {

	@Override
	public String pong(String message) {
		return "Pong from service to [" + message + "]";
	}

}
