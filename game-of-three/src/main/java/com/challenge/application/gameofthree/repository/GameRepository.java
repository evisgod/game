package com.challenge.application.gameofthree.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.application.gameofthree.entity.Game;
import com.challenge.application.gameofthree.utilities.GameStatus;
import com.challenge.application.gameofthree.utilities.GameType;

@Repository
public interface GameRepository extends CrudRepository<Game, Long>{
	List<Game> findByGameTypeAndGameStatus(GameType GameType, GameStatus GameStatus);
    List<Game> findByGameStatus(GameStatus gameStatus);
}
