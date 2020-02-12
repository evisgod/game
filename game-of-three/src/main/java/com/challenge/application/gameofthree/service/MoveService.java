package com.challenge.application.gameofthree.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.application.gameofthree.domain.MoveDTO;
import com.challenge.application.gameofthree.engine.GameEngine;
import com.challenge.application.gameofthree.engine.MoveEvent;
import com.challenge.application.gameofthree.entity.Game;
import com.challenge.application.gameofthree.entity.Move;
import com.challenge.application.gameofthree.entity.Player;
import com.challenge.application.gameofthree.repository.MoveRepository;
import com.challenge.application.gameofthree.utilities.GameStatus;

@Service
public class MoveService {
	private final Logger log = LoggerFactory.getLogger(MoveService.class);

	@Autowired
	private MoveRepository moveRepository;

	@Autowired
	private GameService gameService;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	public void createMove(Game game, Player player, int number) {
		if (number < 1) {
			throw new IllegalArgumentException("Invalid number. Number should be >= 1");
		}
		if (!isValidTurn(game)) {
			throw new IllegalArgumentException("Invalid move. Not your turn.");
		}
		if (number == 1) {
			log.info(player.getName() + " : Congratulations.. You WON !!");
			gameService.updateGameStatus(game, GameStatus.FINISHED);
		}
		Move move = new Move(game, player, number, new Date());
		moveRepository.save(move);
		if (game.getFirstPlayer().isAuto() && game.getSecondPlayer().isAuto()) {
			Object event = new MoveEvent(move);
			applicationEventPublisher.publishEvent(event);
		}
	}

	public int getTheNumberOfPlayerMovesInGame(Game game, Player player) {
		return moveRepository.countByGameAndPlayer(game, player);
	}

	public boolean isValidTurn(Game game) {
		return GameEngine.playerTurn(getTheNumberOfPlayerMovesInGame(game, game.getFirstPlayer()),
				getTheNumberOfPlayerMovesInGame(game, game.getSecondPlayer()));
	}

	public List<MoveDTO> getMovesInGame(Game game) {
		List<Move> moves = moveRepository.findByGame(game);
		List<MoveDTO> movesInGame = new ArrayList<>();
		for (Move move : moves) {
			MoveDTO moveDTO = new MoveDTO(move.getPlayer().getName(), move.getCurrentNumber(), move.getCreated(),
					game.getGameStatus());
			movesInGame.add(moveDTO);
		}
		return movesInGame;
	}

	public MoveDTO getLastMove(Game game) {
		List<Move> moves = moveRepository.findByGame(game);
		Move lastMove = moves.get(moves.size() - 1);
		return new MoveDTO(lastMove.getPlayer().getName(), lastMove.getCurrentNumber(), lastMove.getCreated(),
				game.getGameStatus());
	}
}
