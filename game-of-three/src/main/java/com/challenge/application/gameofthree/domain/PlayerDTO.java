package com.challenge.application.gameofthree.domain;

public class PlayerDTO {
	private String name;
	private boolean isAuto;

	public PlayerDTO() {
	}

	public PlayerDTO(String name, boolean isAuto) {
		this.name = name;
		this.isAuto = isAuto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAuto() {
		return isAuto;
	}

	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		PlayerDTO other = (PlayerDTO) obj;
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
		builder.append("PlayerDTO [name=");
		builder.append(name);
		builder.append(", isAuto=");
		builder.append(isAuto);
		builder.append("]");
		return builder.toString();
	}
}