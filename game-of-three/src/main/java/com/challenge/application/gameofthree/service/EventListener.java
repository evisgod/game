package com.challenge.application.gameofthree.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.challenge.application.gameofthree.engine.GameEngine;
import com.challenge.application.gameofthree.engine.GameEvent;
import com.challenge.application.gameofthree.engine.MoveEvent;
import com.challenge.application.gameofthree.entity.Move;
import com.challenge.application.gameofthree.utilities.GameStatus;


/**
 * EventListener class listening to Game and Move events. 
 * @author Vishnu
 *
 */
@Component
public class EventListener {

	@Autowired
	GameEngine gameEngine;

	@org.springframework.context.event.EventListener({ MoveEvent.class, GameEvent.class })
	public void handleMove(Object event) {
		if (event instanceof MoveEvent) {
			MoveEvent moveEvent = (MoveEvent) event;
			if (moveEvent.getMove().getGame().getGameStatus() != GameStatus.FINISHED) {
				gameEngine.sendMove(moveEvent.getMove());
			}
		} else if (event instanceof GameEvent) {
			GameEvent gameEvent = (GameEvent) event;
//			if (gameEvent.getGame().getFirstPlayer().isAuto()) {
				Move move = new Move(gameEvent.getGame(), gameEvent.getGame().getFirstPlayer(), gameEngine.getRandomNumber(), new Date());
				gameEngine.startGame(move);
//			}
		}
	}
}
