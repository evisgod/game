package com.application.player.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.application.player.domain.CreateMoveDTO;
import com.application.player.domain.Game;
import com.application.player.domain.GameDTO;
import com.application.player.domain.GameResponse;
import com.application.player.domain.GameStatus;
import com.application.player.domain.Player;
import com.application.player.domain.PlayerDTO;

@Service
public class PlayerService {

	private final Logger log = LoggerFactory.getLogger(PlayerService.class);

	@Value("${game.server.url}")
	private String serverUrl;

	@Value("${player.name}")
	private String playerName;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		restTemplate = new RestTemplate();
		((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(10000);
		return restTemplate;
	}

	public ResponseEntity<Player> createPlayer(PlayerDTO playerDTO) {
		final HttpEntity<PlayerDTO> requestEntity = new HttpEntity<PlayerDTO>(playerDTO);
		return restTemplate.exchange(serverUrl + "/player", HttpMethod.POST, requestEntity, Player.class);
	}

	public ResponseEntity<Game> createGame(Player player) {
		// get Games to join
		final HttpEntity<String> requestEntity = new HttpEntity<String>(player.getName());
		String url = serverUrl + "/game/list";
		GameResponse gameResponse = restTemplate.getForObject(getServerUrl(url, player.getName()).toUriString(),
				GameResponse.class);
		Game gameToJoin = null;
		if (gameResponse != null) {
			List<Game> game = gameResponse.getGameList();
			gameToJoin = game.stream().filter(g -> GameStatus.WAIT_FOR_PLAYER == g.getGameStatus()).findFirst()
					.orElse(null);
		}

		// if no game found then create a new game, else join the existing one.
		ResponseEntity<Game> newGame = null;
		if (gameToJoin != null) {
			String joinUrl = serverUrl + "/game/join";
			GameDTO gameDTO = new GameDTO(gameToJoin.getId(), gameToJoin.getGameType());
			final HttpEntity<GameDTO> request = new HttpEntity<GameDTO>(gameDTO);
			newGame = restTemplate.postForEntity(getServerUrl(joinUrl, player.getName()).toUriString(), request,
					Game.class);
		} else {
			String joinUrl = serverUrl + "/game/create";
			newGame = restTemplate.postForEntity(getServerUrl(joinUrl, player.getName()).toUriString(), requestEntity,
					Game.class);
		}
		return newGame;
	}

	public ResponseEntity<Void> createMove(CreateMoveDTO createMoveDTO) {
		createMoveDTO.setPlayerName(playerName);
		createMoveDTO.setNumber(calculateNumber(createMoveDTO.getNumber()));
		final HttpEntity<CreateMoveDTO> requestEntity = new HttpEntity<CreateMoveDTO>(createMoveDTO);
		return restTemplate.exchange(serverUrl + "/move", HttpMethod.POST, requestEntity, Void.class);
	}

	private int calculateNumber(int number) {
		int addition = number % 3 == 0 ? 0 : (number % 3 == 1) ? -1 : 1;
		int nextNumber = (number + addition) / 3;
		log.info("Initial Number :" + number + ", added: " + addition + ", and sending Number: " + nextNumber);
		return nextNumber;
	}

	private UriComponentsBuilder getServerUrl(String url, String name) {
		return UriComponentsBuilder.fromHttpUrl(url).queryParam("playerName", name);
	}
}
