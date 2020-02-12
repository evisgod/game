package com.challenge.application.gameofthree.domain;

import javax.validation.constraints.NotNull;

import com.challenge.application.gameofthree.utilities.GameType;

public class GameDTO {
	@NotNull
	private long id;
	@NotNull
	private GameType gameType;

	public GameDTO() {
	}

	public GameDTO(@NotNull long id, @NotNull GameType gameType) {
		this.id = id;
		this.gameType = gameType;
	}

	public long getId() {
		return id;
	}

	public GameType getGameType() {
		return gameType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameType == null) ? 0 : gameType.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		GameDTO other = (GameDTO) obj;
		if (gameType != other.gameType)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GameDTO [id=").append(id).append(", gameType=").append(gameType).append("]");
		return builder.toString();
	}
}
