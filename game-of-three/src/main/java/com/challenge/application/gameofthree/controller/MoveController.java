package com.challenge.application.gameofthree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.application.gameofthree.domain.CreateMoveDTO;
import com.challenge.application.gameofthree.domain.MoveDTO;
import com.challenge.application.gameofthree.entity.Game;
import com.challenge.application.gameofthree.service.GameService;
import com.challenge.application.gameofthree.service.MoveService;
import com.challenge.application.gameofthree.service.PlayerService;

/**
 * Move controller to create and fetch moves.
 * 
 * @author Vishnu
 *
 */
@RestController
@RequestMapping("/move")
public class MoveController {
	@Autowired
	private PlayerService playerService;

	@Autowired
	private GameService gameService;

	@Autowired
	private MoveService moveService;

	@PostMapping
	public void nextMove(@RequestBody CreateMoveDTO createMoveDTO) {
		Game game = gameService.getGame(createMoveDTO.getGameId());
		moveService.createMove(game, playerService.getPlayerByName(createMoveDTO.getPlayerName()),
				createMoveDTO.getNumber());
		gameService.updateGameStatus(game, gameService.checkGameStatus(game));
	}

	@GetMapping("/last")
	public MoveDTO getLastMove(@RequestParam Long gameId) {
		Game game = gameService.getGame(gameId);
		return moveService.getLastMove(game);
	}

	@GetMapping("/list")
	public List<MoveDTO> getMovesInGame(@RequestParam Long gameId) {
		Game game = gameService.getGame(gameId);
		return moveService.getMovesInGame(game);
	}
}
