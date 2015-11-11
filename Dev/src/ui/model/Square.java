/**
 * Simulateur de foule
 * 
 * Kevin DHORNE, Fabien GAMELIN, Ismaïl NGUYEN
 * 
 * ESGI 2015/2016
 */

package ui.model;

import java.util.List;
import utils.ConfigUtil;

public class Square {

	private char type;
	private Minion mouse = null;
	private SpecialPoint pos = null;
	private char grass, wall, departure;
	private Square finish;
	private int nb_souris_restant = 0; // Utilisé uniquement pour le cas des portes: Nb de souris restant devant sortir
	private List<Square> parcours_porte;  // Uniquement pour case départ (Objectif par défaut)

	public Square(int x, int y, char type) {
		super();
		
		grass = ConfigUtil.getCharOf("grass");
		wall = ConfigUtil.getCharOf("wall");
		departure = ConfigUtil.getCharOf("departure");

		pos = new SpecialPoint(x, y);
		setType(type);
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	public int getNb_souris_restant() {
		return nb_souris_restant;
	}

	public void setNb_souris_restant(int nb_souris_restant) {
		this.nb_souris_restant = nb_souris_restant;
	}

	public List<Square> getParcours_porte() {
		return parcours_porte;
	}

	public void setParcours_porte(List<Square> parcours_porte) {
		this.parcours_porte = parcours_porte;
	}

	public Minion getMouse() {
		return mouse;
	}
	
	public void setMouse(Minion mouse, List<Square> listCases) {
		mouse.setTime(this.getTime());
		mouse.setInitialTarget(listCases);
		this.mouse = mouse;
	}
	
	public void unsetMouse() {
		this.mouse = null;
	}
	
	public void setMouse(Minion mouse, Square fin, List<Square> listCases) {
		mouse.setObjectif(fin);
		mouse.setTime(this.getTime());
		mouse.setInitialTarget(listCases);
		this.mouse = mouse;
	}

	public SpecialPoint getPos() {
		return pos;
	}

	public void setPos(SpecialPoint pos) {
		this.pos = pos;
	}

	public Square getFinish() {
		return finish;
	}

	public void setFinish(Square f) {
		this.finish = f;
	}

	public boolean isAvailable() {
		return this.type != wall
				&& this.type != departure
				&& this.mouse == null;
		/*
			return ((this.type != ConfigUtil.getCharOf("wall") 
				&& this.type != ConfigUtil.getCharOf("departure"))
				&& (this.mouse == null));
		 */
	}

	public boolean isFinish() {
		return this.type == ConfigUtil.getCharOf("arrival");
	}

	public int getTime() {
		return this.type == grass ? 2 : 1;
		/*
		  	return this.type == ConfigUtil.getCharOf("grass") ? 2 : 1;
		 */
	}

	public void decrement_nb_souris_restant() {
		nb_souris_restant--;
	}
}
