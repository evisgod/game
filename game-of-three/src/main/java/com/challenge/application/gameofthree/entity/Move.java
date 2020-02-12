package com.challenge.application.gameofthree.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Move")
public class Move implements Serializable {
	private static final long serialVersionUID = -3211907482189025268L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MOVE_ID", updatable = false, nullable = false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "GAME_ID", nullable = false)
	private Game game;

	@ManyToOne
	@JoinColumn(name = "PLAYER_ID", nullable = true)
	private Player player;

	@Column(name = "CURRENT_NUMBER", nullable = false)
	private int currentNumber;

	@Column(name = "CREATED", nullable = false)
	private Date created;

	public Move() {
	}

	public Move(Game game, Player player, int currentNumber, Date created) {
		this.game = game;
		this.player = player;
		this.currentNumber = currentNumber;
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayerId(Player player) {
		this.player = player;
	}

	public int getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
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
		builder.append("Move [id=");
		builder.append(id);
		builder.append(", game=");
		builder.append(game);
		builder.append(", player=");
		builder.append(player);
		builder.append(", currentNumber=");
		builder.append(currentNumber);
		builder.append(", created=");
		builder.append(created);
		builder.append("]");
		return builder.toString();
	}
}