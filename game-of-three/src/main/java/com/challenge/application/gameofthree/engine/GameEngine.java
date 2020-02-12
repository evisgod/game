package com.challenge.application.gameofthree.engine;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.challenge.application.gameofthree.domain.CreateMoveDTO;
import com.challenge.application.gameofthree.entity.Move;
import com.challenge.application.gameofthree.service.MoveService;

@Component
public class GameEngine {

	@Value("${player.first.url}")
	private String firstPlayerUrl;

	@Value("${player.second.url}")
	private String secondPlayerUrl;
	
	@Value("${game.minNumber}")
	private int minNumber;
	
	@Value("${game.maxNumber}")
	private int maxNumber;

	@Autowired
	MoveService moveService;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		restTemplate = new RestTemplate();
		((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(10000);
		return restTemplate;
	}

	public static boolean playerTurn(int numberOfFirstPlayerMovesInGame, int numberOfSecondPlayerMovesInGame) {
		return numberOfFirstPlayerMovesInGame == numberOfSecondPlayerMovesInGame || numberOfFirstPlayerMovesInGame == 0;
	}
	
	public void sendMove(Move move) {
		CreateMoveDTO createMoveDTO = new CreateMoveDTO(move.getPlayer().getName(), move.getGame().getId(),
				move.getCurrentNumber());
		final HttpEntity<CreateMoveDTO> requestEntity = new HttpEntity<CreateMoveDTO>(createMoveDTO);
		String serverUrl = getUrl(move.getPlayer().getName());
		restTemplate.exchange(serverUrl + "/move", HttpMethod.POST, requestEntity, Integer.class);
	}

	private String getUrl(String name) {
		if (firstPlayerUrl.contains(name)) {
			return secondPlayerUrl;
		}
		return firstPlayerUrl;
	}

	public void startGame(Move move) {
		moveService.createMove(move.getGame(), move.getPlayer(), move.getCurrentNumber());
	}
	
	public int getRandomNumber() {
		return new Random().nextInt(maxNumber-minNumber)+minNumber;
		
	}
}
