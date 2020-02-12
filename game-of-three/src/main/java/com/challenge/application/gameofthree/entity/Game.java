package com.challenge.application.gameofthree.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.challenge.application.gameofthree.utilities.GameStatus;
import com.challenge.application.gameofthree.utilities.GameType;

@Entity
@Table(name = "Game")
public class Game implements Serializable {
	
	private static final long serialVersionUID = -7786964563526020490L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GAME_ID", updatable = false, nullable = false)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "SECOND_PLAYER_ID", nullable = true)
	private Player secondPlayer;
	
	@ManyToOne
	@JoinColumn(name = "FIRST_PLAYER_ID", nullable = false)
	private Player firstPlayer;
	
	@Enumerated(EnumType.STRING)
	private GameType gameType;
	
	@Enumerated(EnumType.STRING)
	private GameStatus gameStatus;
	
	@Column(name = "CREATED", nullable = false)
	private Date created;

	public Game() {}

	public Game(Player firstPlayer, Player secondPlayer, GameType gameType, GameStatus gameStatus,
			Date created) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.gameType = gameType;
		this.gameStatus = gameStatus;
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Game [id=");
		builder.append(id);
		builder.append(", firstPlayer=");
		builder.append(firstPlayer);
		builder.append(", secondPlayer=");
		builder.append(secondPlayer);
		builder.append(", gameType=");
		builder.append(gameType);
		builder.append(", gameStatus=");
		builder.append(gameStatus);
		builder.append(", created=");
		builder.append(created);
		builder.append("]");
		return builder.toString();
	}
}