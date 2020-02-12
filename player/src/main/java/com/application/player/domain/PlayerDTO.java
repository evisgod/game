package com.application.player.domain;


public class PlayerDTO {
    private String name;
    private boolean isAuto;
    
	public PlayerDTO() {}

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
    
}