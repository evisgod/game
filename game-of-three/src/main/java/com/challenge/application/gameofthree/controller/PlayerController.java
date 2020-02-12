package com.challenge.application.gameofthree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.application.gameofthree.domain.PlayerDTO;
import com.challenge.application.gameofthree.entity.Player;
import com.challenge.application.gameofthree.service.PlayerService;


/**
 * Player controller to create and list players.
 * @author Vishnu
 *
 */
@RestController
@RequestMapping("/player")
public class PlayerController {
	@Autowired
	PlayerService playerService;

	@PostMapping
	public Player createPlayer(@RequestBody PlayerDTO playerDTO) {
		return playerService.createNewPlayer(playerDTO);
	}

	@GetMapping
	public List<Player> getPlayers() {
		return playerService.listPlayers();
	}
}