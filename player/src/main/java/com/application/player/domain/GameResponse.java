package com.application.player.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameResponse {
	private List<Game> gameList;

	public GameResponse() {
		gameList = new ArrayList<>();
		
	}
	
	public GameResponse(List<Game> gameList) {
		super();
		this.gameList = gameList;
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GameResponse [gameList=");
		builder.append(gameList);
		builder.append("]");
		return builder.toString();
	}
	
}