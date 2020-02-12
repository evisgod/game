package com.challenge.application.gameofthree;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import com.challenge.application.gameofthree.controller.GameController;
import com.challenge.application.gameofthree.controller.MoveController;
import com.challenge.application.gameofthree.controller.PlayerController;
import com.challenge.application.gameofthree.domain.CreateMoveDTO;
import com.challenge.application.gameofthree.domain.GameDTO;
import com.challenge.application.gameofthree.domain.GameResponse;
import com.challenge.application.gameofthree.domain.PlayerDTO;
import com.challenge.application.gameofthree.entity.Game;
import com.challenge.application.gameofthree.entity.Move;
import com.challenge.application.gameofthree.entity.Player;
import com.challenge.application.gameofthree.repository.GameRepository;
import com.challenge.application.gameofthree.repository.MoveRepository;
import com.challenge.application.gameofthree.service.PlayerService;
import com.challenge.application.gameofthree.utilities.GameStatus;
import com.challenge.application.gameofthree.utilities.GameType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameOfThreeApplicationTests {

	@Autowired
	private GameController gameController;
	@Autowired
	private PlayerController playerController;
	@Autowired
	private MoveController moveController;
	@MockBean
	private PlayerService playerService;
	@MockBean
	private GameRepository mockGameRepository;
	@MockBean
	private MoveRepository mockMoveRepository;
	@MockBean
	private ApplicationEventPublisher applicationEventPublisher;
	private PlayerDTO playerDTO;
	private Player player;
	private Game game;

	@Before
	public void setup() {
		playerDTO = new PlayerDTO("P1", true);
		player = new Player("P1", true);
		game = new Game(player, null, GameType.MACHINE, GameStatus.WAIT_FOR_PLAYER, new Date());
	}

	@Test
	public void createGameTest() {
		// Mockito.when(gameService.createNewGame(player)).thenReturn(game);
		Mockito.when(playerService.getPlayerByName("P1")).thenReturn(player);
		Game game = gameController.createNewGame("P1");
		assertTrue(game.getGameType().equals(GameType.MACHINE));
	}

	@Test
	public void getGamesToJoinTest() {
		List<Game> gameList = new ArrayList<Game>();
		gameList.add(game);
		Mockito.when(mockGameRepository.findByGameStatus(GameStatus.WAIT_FOR_PLAYER)).thenReturn(gameList);
		Mockito.when(playerService.getPlayerByName("P1")).thenReturn(new Player("P1", true));
		GameResponse response = gameController.getGamesToJoin("P2");
		assertTrue(response.getGameList().size() == 1);
	}

	@Test
	public void getGamesToJoin_NoGameAvailable_Test() {
		Mockito.when(playerService.getPlayerByName("P1")).thenReturn(player);
		GameResponse response = gameController.getGamesToJoin("P1");
		assertTrue(response.getGameList().size() == 0);
	}

	@Test
	public void joinGameTest() {
		player.setAuto(false);
		Mockito.when(playerService.getPlayerByName("P2")).thenReturn(new Player("P2", false));
		Mockito.when(mockGameRepository.findById(1L)).thenReturn(Optional.of(game));
		Mockito.doNothing().when(applicationEventPublisher).publishEvent(Matchers.any(Object.class));
		GameDTO gameDTO = new GameDTO(1, GameType.MACHINE);
		Game game = gameController.joinGame("P2", gameDTO);
		System.out.println("Game" + game);
		assertTrue(game.getSecondPlayer().getName().equals("P2"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nextMoveTest_InvalidNumber() {
		Mockito.when(mockGameRepository.findById(1L)).thenReturn(Optional.of(game));
		Mockito.when(playerService.getPlayerByName("P1")).thenReturn(player);
		CreateMoveDTO createMoveDTO = new CreateMoveDTO("P1", 1, 0);
		moveController.nextMove(createMoveDTO);
	}

	@Test
	public void nextMoveTest() {
		player.setAuto(false);
		game.setId(1L);
		game.setSecondPlayer(new Player("P2", false));
		Mockito.when(mockGameRepository.findById(1L)).thenReturn(Optional.of(game));
		Mockito.when(playerService.getPlayerByName("P1")).thenReturn(player);
		CreateMoveDTO createMoveDTO = new CreateMoveDTO("P1", 1, 20);
		moveController.nextMove(createMoveDTO);
		verify(mockMoveRepository, times(1)).save(Mockito.any(Move.class));
	}

	@Test
	public void createPlayerTest() {
		Mockito.when(playerService.createNewPlayer(playerDTO)).thenReturn(player);
		Player player = playerController.createPlayer(playerDTO);
		assertTrue(player.getName().equals("P1"));
	}
}
