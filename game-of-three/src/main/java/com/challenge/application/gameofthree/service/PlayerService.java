package com.challenge.application.gameofthree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.application.gameofthree.domain.PlayerDTO;
import com.challenge.application.gameofthree.entity.Player;
import com.challenge.application.gameofthree.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;

	public Player getPlayerByName(String playerName) {
		return playerRepository.findOneByName(playerName);
	}

	@Transactional
	public Player createNewPlayer(PlayerDTO newPlayerDTO) {
		Player player = new Player(newPlayerDTO.getName(), newPlayerDTO.isAuto());
		playerRepository.save(player);
		return player;
	}

	public List<Player> listPlayers() {
		return (List<Player>) playerRepository.findAll();
	}
}
