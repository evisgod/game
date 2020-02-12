package com.application.player.domain;

public class CreateMoveDTO {
	private String playerName;
	private long gameId;
	private int number;

	public CreateMoveDTO() {
	}

	public CreateMoveDTO(String playerName, long gameId, int number) {
		super();
		this.playerName = playerName;
		this.gameId = gameId;
		this.number = number;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (gameId ^ (gameId >>> 32));
		result = prime * result + number;
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
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
		CreateMoveDTO other = (CreateMoveDTO) obj;
		if (gameId != other.gameId)
			return false;
		if (number != other.number)
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ Player");
		builder.append(playerName);
		builder.append(", with gameId=");
		builder.append(gameId);
		builder.append(", sending number=");
		builder.append(number);
		builder.append("]");
		return builder.toString();
	}
}