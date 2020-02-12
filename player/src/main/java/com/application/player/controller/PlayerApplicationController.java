package com.application.player.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.player.domain.CreateMoveDTO;
import com.application.player.service.PlayerService;


/**
 * Player application controller to send moves.
 * @author Vishnu
 *
 */
@RestController
@RequestMapping("${player.name}/move")
public class PlayerApplicationController {
	
	@Autowired
	PlayerService playerService;
	
	@PostMapping
	public void createMove(@RequestBody CreateMoveDTO createMoveDTO) {
		playerService.createMove(createMoveDTO);
	}
}
