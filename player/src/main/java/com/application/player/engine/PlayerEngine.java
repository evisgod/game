package com.application.player.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.application.player.domain.Player;
import com.application.player.domain.PlayerDTO;
import com.application.player.service.PlayerService;

@Component
public class PlayerEngine implements ApplicationRunner{
	
	@Value("${player.name}")
	private String playerName;
	
	@Value("${player.auto}")
	private boolean isAuto;
	
	@Autowired
	PlayerService playerService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		PlayerDTO playerDTO = new PlayerDTO(playerName, isAuto);
		ResponseEntity<Player> player = playerService.createPlayer(playerDTO);
		playerService.createGame(player.getBody());
	}
}
