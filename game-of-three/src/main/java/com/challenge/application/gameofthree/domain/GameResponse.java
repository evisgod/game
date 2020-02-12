package com.challenge.application.gameofthree.domain;

import java.util.List;

import com.challenge.application.gameofthree.entity.Game;

public class GameResponse {
	private List<Game> gameList;

	public GameResponse() {
	}

	public GameResponse(List<Game> gameList) {
		this.gameList = gameList;
	}

	public List<Game> getGameList() {
		return gameList;
	}

	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameList == null) ? 0 : gameList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameResponse other = (GameResponse) obj;
		if (gameList == null) {
			if (other.gameList != null)
				return false;
		} else if (!gameList.equals(other.gameList))
			return false;
		return true;
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
