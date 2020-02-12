package com.challenge.application.gameofthree.domain;

import java.util.Date;

import com.challenge.application.gameofthree.utilities.GameStatus;

public class MoveDTO {
	private String player;
	private int number;
	private Date created;
	private GameStatus gameStatus;

	public MoveDTO() {
	}

	public MoveDTO(String player, int number, Date created, GameStatus gameStatus) {
		this.player = player;
		this.number = number;
		this.created = created;
		this.gameStatus = gameStatus;
	}

	public String getPlayer() {
		return player;
	}

	public int getNumber() {
		return number;
	}

	public Date getCreated() {
		return created;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((gameStatus == null) ? 0 : gameStatus.hashCode());
		result = prime * result + number;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
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
		MoveDTO other = (MoveDTO) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (gameStatus != other.gameStatus)
			return false;
		if (number != other.number)
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MoveDTO [player=");
		builder.append(player);
		builder.append(", number=");
		builder.append(number);
		builder.append(", created=");
		builder.append(created);
		builder.append(", gameStatus=");
		builder.append(gameStatus);
		builder.append("]");
		return builder.toString();
	}
}