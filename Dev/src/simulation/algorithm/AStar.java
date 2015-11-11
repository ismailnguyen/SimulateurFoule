/**
 * Simulateur de foule
 * 
 * Kevin DHORNE, Fabien GAMELIN, Ismaïl NGUYEN
 * 
 * ESGI 2015/2016
 */

package simulation.algorithm;

import ui.model.Square;

public class AStar {

	private Square current;
	private AStar previous;
	private int distance;
	private int heuristic;

	public AStar(Square current, int distance, int heuristic) {
		super();
		this.current = current;
		this.distance = distance;
		this.heuristic = heuristic;
	}

	public Square getCurrent() {
		return current;
	}

	public void setCurrent(Square current) {
		this.current = current;
	}

	public AStar getPrevious() {
		return previous;
	}

	public void setPrevious(AStar previous) {
		this.previous = previous;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
}
