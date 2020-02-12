package com.challenge.application.gameofthree.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.challenge.application.gameofthree.domain.GameDTO;
import com.challenge.application.gameofthree.domain.GameResponse;
import com.challenge.application.gameofthree.engine.GameEvent;
import com.challenge.application.gameofthree.entity.Game;
import com.challenge.application.gameofthree.entity.Player;
import com.challenge.application.gameofthree.repository.GameRepository;
import com.challenge.application.gameofthree.utilities.GameStatus;
import com.challenge.application.gameofthree.utilities.GameType;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public Game createNewGame(Player player) {
		Game game = new Game(player, null, player.isAuto() ? GameType.MACHINE : GameType.HUMAN,
				GameStatus.WAIT_FOR_PLAYER, new Date());
		gameRepository.save(game);
		return game;
	}

	public GameResponse getGamesToJoin(Player player) {
		GameResponse gameResponse = new GameResponse();
		List<Game> gameList = gameRepository.findByGameStatus(GameStatus.WAIT_FOR_PLAYER).stream()
				.filter(game -> game.getFirstPlayer() != player).collect(Collectors.toList());
		gameResponse.setGameList(gameList);
		return gameResponse;
	}

	public Game joinGame(Player player, GameDTO gameDTO) {
		Game game = getGame(gameDTO.getId());
		game.setSecondPlayer(player);
		Object event = new GameEvent(game);
		gameRepository.save(game);
		updateGameStatus(game, GameStatus.IN_PROGRESS);
		applicationEventPublisher.publishEvent(event);
		return game;
	}

	public List<Game> getPlayerGames(Player player) {
		return gameRepository.findByGameStatus(GameStatus.IN_PROGRESS).stream()
				.filter(game -> game.getFirstPlayer() == player).collect(Collectors.toList());
	}

	public Game getGame(Long id) {
		Optional<Game> game = gameRepository.findById(id);
		return game.isPresent() ? game.get() : null;
	}

	public GameStatus checkGameStatus(Game game) {
		return getGame(game.getId()).getGameStatus();
	}

	public void updateGameStatus(Game game, GameStatus currentStatus) {
		game.setGameStatus(currentStatus);
		gameRepository.save(game);
	}
}
