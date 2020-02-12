package com.challenge.application.gameofthree.engine;

import org.springframework.context.ApplicationEvent;

import com.challenge.application.gameofthree.entity.Move;

public class MoveEvent extends ApplicationEvent{
	private static final long serialVersionUID = -3245739911062475743L;

	public MoveEvent(Move move) {
		super(move);
	}
	
	public Move getMove() {
		return (Move) getSource();
	}
}
