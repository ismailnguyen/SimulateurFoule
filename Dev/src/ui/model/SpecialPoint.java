/**
 * Simulateur de foule
 * 
 * Kevin DHORNE, Fabien GAMELIN, Isma�l NGUYEN
 * 
 * ESGI 2015/2016
 */

package ui.model;

public class SpecialPoint {

	protected int x;
	protected int y;

	public SpecialPoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "[" + x + "; " + y + "]";
	}
}
