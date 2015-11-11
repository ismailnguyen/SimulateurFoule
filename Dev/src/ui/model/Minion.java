/**
 * Simulateur de foule
 * 
 * Kevin DHORNE, Fabien GAMELIN, Ismaïl NGUYEN
 * 
 * ESGI 2015/2016
 */

package ui.model;

import java.util.List;
import java.util.UUID;

public class Minion {

	private UUID id;
	private int move;
	private int time;
	private Square objectif;
	private List<Square> initialTarget;


	public Minion(UUID id) {
		super();
		this.id = id;
		this.move = 0;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public Square getObjectif() {
		return objectif;
	}

	public void setObjectif(Square objectif) {
		this.objectif = objectif;
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void move() {
		this.move++;
	}

	public List<Square> getInitialTarget() {
		return initialTarget;
	}

	public void setInitialTarget(List<Square> it) {
		this.initialTarget = it;
	}

}
