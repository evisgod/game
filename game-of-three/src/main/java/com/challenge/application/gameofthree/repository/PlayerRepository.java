package com.challenge.application.gameofthree.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.application.gameofthree.entity.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long>{
	Player findOneByName(String name);
}
