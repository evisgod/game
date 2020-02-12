package com.challenge.application.gameofthree.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.application.gameofthree.entity.Game;
import com.challenge.application.gameofthree.entity.Move;
import com.challenge.application.gameofthree.entity.Player;

@Repository
public interface MoveRepository extends CrudRepository<Move, Long>{
	List<Move> findByGame(Game game);
    List<Move> findByGameAndPlayer(Game game, Player player);
    int countByGameAndPlayer(Game game, Player player);
}
