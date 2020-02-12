package com.challenge.application.gameofthree.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.application.gameofthree.domain.GameDTO;
import com.challenge.application.gameofthree.domain.GameResponse;
import com.challenge.application.gameofthree.entity.Game;
import com.challenge.application.gameofthree.service.GameService;
import com.challenge.application.gameofthree.service.PlayerService;

/**
 * Game controller to create, list and join game.
 * @author Vishnu
 *
 */
@RestController
@RequestMapping("/game")
public class GameController {

	private final Logger log = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameService gameService;

	@Autowired
	private PlayerService playerService;

	@PostMapping("/create")
	public Game createNewGame(@RequestParam String playerName) {
		Game game = gameService.createNewGame(playerService.getPlayerByName(playerName));
		log.info("New game has been created with id: " + game.getId());
		return game;
	}

	@GetMapping("/list")
	public GameResponse getGamesToJoin(@RequestParam String playerName) {
		return gameService.getGamesToJoin(playerService.getPlayerByName(playerName));
	}

	@PostMapping("/join")
	public Game joinGame(@RequestParam String playerName, @RequestBody @Validated GameDTO gameDTO) {
		return gameService.joinGame(playerService.getPlayerByName(playerName), gameDTO);
	}

	@PostMapping("/player/list")
	public List<Game> getPlayerGames(@RequestParam String playerName) {
		return gameService.getPlayerGames(playerService.getPlayerByName(playerName));
	}

	@GetMapping
	public Game getGameProperties(@RequestParam Long id) {
		return gameService.getGame(id);
	}
}
