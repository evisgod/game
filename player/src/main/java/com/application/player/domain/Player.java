package com.application.player.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {
	private long id;
	private String name;
	private boolean isAuto;

	public Player() {
	}

	public Player(long id, String name, boolean isAuto) {
		super();
		this.id = id;
		this.name = name;
		this.isAuto = isAuto;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isAuto() {
		return isAuto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isAuto ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		if (isAuto != other.isAuto)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", isAuto=");
		builder.append(isAuto);
		builder.append("]");
		return builder.toString();
	}
}
