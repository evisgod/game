package com.challenge.application.gameofthree.engine;

import org.springframework.context.ApplicationEvent;

import com.challenge.application.gameofthree.entity.Game;

public class GameEvent extends ApplicationEvent {
	private static final long serialVersionUID = -6304643888742367648L;

	public GameEvent(Game game) {
		super(game);
	}

	public Game getGame() {
		return (Game) getSource();
	}
}