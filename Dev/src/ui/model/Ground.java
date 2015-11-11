/**
 * Simulateur de foule
 * 
 * Kevin DHORNE, Fabien GAMELIN, Ismaïl NGUYEN
 * 
 * ESGI 2015/2016
 */

package ui.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import utils.ConfigUtil;
import utils.MapUtil;

public class Ground {

	private Square[][] map;
	private int length, width, souris_arrivees, tour, souris_sorties, nb_deplacements_total = 0;

	public Ground(String file) throws java.io.FileNotFoundException {

		String ligne = null;
		ArrayList<String> ar = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(file));

		try {
			while ((ligne = br.readLine()) != null) {
				ar.add(ligne);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		int x = ar.size();
		int y = ar.get(0).length();

		this.length = ar.size();
		this.width = ar.get(0).length();

		map = new Square[x][y];

		for (int i = 0; i < ar.size(); i++) {
			for (int j = 0; j < ar.get(i).length(); j++) {
				addTypeCase(i, j, ar.get(i).charAt(j));
				if (ar.get(i).charAt(j) == ConfigUtil.getCharOf("departure")) {
					map[i][j].setNb_souris_restant(Integer.parseInt(ConfigUtil.getConfiguration("starters")));
				}
			}
		}
		
		// Un seconde boucle sur la map pour définir les "objectif par défaut"
		for (int i = 0; i < getCaseByType(ConfigUtil.getCharOf("departure")).size(); i++) {
			Square start  = getCaseByType(ConfigUtil.getCharOf("departure")).get(i);
			for (int j = 0; j < getCaseByType(ConfigUtil.getCharOf("arrival")).size(); j++) {
				Square finish  = getCaseByType(ConfigUtil.getCharOf("arrival")).get(j);
				List<Square> list = MapUtil.search(this, start, finish);
				if ((list != null) && (getCaseByType(ConfigUtil.getCharOf("departure")).get(i).getParcours_porte() == null || start.getParcours_porte().size() > list.size())) {
					map[start.getPos().getX()][start.getPos().getY()].setParcours_porte(list);
				}
			}
		}
	}

	public Square[][] getMap() {
		return map;
	}

	public void setMap(Square[][] map) {
		this.map = map;
	}

	public void addTypeCase(int x, int y, char type){
		map[x][y] = new Square(x, y, type);
	}

	public String printCase(int x, int y){
		if(map[x][y].getType() == ConfigUtil.getCharOf("wall"))
			return "Wall";
		
		if(map[x][y].getType() == ConfigUtil.getCharOf("space"))
			return "Empty";
		
		if(map[x][y].getType() == ConfigUtil.getCharOf("grass"))
			return "Grass";
		
		if(map[x][y].getType() == ConfigUtil.getCharOf("departure"))
			return "Start";
		
		if(map[x][y].getType() == ConfigUtil.getCharOf("arrival"))
			return "Finish";
		
		return null;
	}

	public List<Square> getCaseByType(char type){

		List<Square> listCases = new ArrayList<Square>();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j].getType() == type) {
					listCases.add(map[i][j]);
				}
			}
		}
		return listCases;
	}

	public List<Square> getNeighbour(Square mouse){

		List<Square> neighbours = new ArrayList<Square>();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j] == mouse) {
					if(i>0 && j>0 && map[i-1][j-1].isAvailable() && (map[i-1][j].isAvailable() || map[i][j-1].isAvailable())) neighbours.add(map[i-1][j-1]);
					if(j>0 && map[i][j-1].isAvailable()) neighbours.add(map[i][j-1]);
					if(i<map.length-1 && j >0 && map[i+1][j-1].isAvailable() && (map[i][j-1].isAvailable() || map[i+1][j].isAvailable())) neighbours.add(map[i+1][j-1]);
					if(i>0 && map[i-1][j].isAvailable()) neighbours.add(map[i-1][j]);
					if(i<map.length-1 && map[i+1][j].isAvailable()) neighbours.add(map[i+1][j]);
					if(i>0 && j<map[0].length-1 && map[i-1][j+1].isAvailable() && (map[i-1][j].isAvailable() || map[i][j+1].isAvailable())) neighbours.add(map[i-1][j+1]);
					if(j<map[0].length-1 && map[i][j+1].isAvailable()) neighbours.add(map[i][j+1]);
					if(i<map.length-1 && j<map[0].length-1 && map[i+1][j+1].isAvailable() && (map[i+1][j].isAvailable() || map[i][j+1].isAvailable())) neighbours.add(map[i+1][j+1]);
				}
			}
		}
		return neighbours;
	}

	public int calcLength(Square c1, Square c2){
		return Math.abs(c1.getPos().getX() - c2.getPos().getX()) + Math.abs(c1.getPos().getY() - c2.getPos().getY()) + c1.getTime() + c2.getTime();
	}

	public Square[][] cloneTable() {
		int i, j;
		Square Double[][];
		Double = new Square[width][length];
		for (i = 0; i < width; i++) {
			for (j = 0; j < length; j++) {
				Double[i][j] = map[i][j];
			}
		}
		return Double;
	}

	public int getArrivedMouses() {
		return souris_arrivees;
	}

	public int getSouris_sorties() {
		return souris_sorties;
	}

	public void setSouris_sorties(int souris_sorties) {
		this.souris_sorties = souris_sorties;
	}

	public void setScore(int sc) {
		souris_arrivees = sc;
	}

	public Square ReadCase(int i, int j) {
		return map[i][j];
	}

	public int getWidth() {
		return width;
	}

	public int getlength() {
		return length;
	}

	public Square[][] getWorld() {
		return map;
	}

	public void tourSuivant() {
		Square start = null;
		List<Square> listCases = null;
		List<Square> listCasesTemp = null;
		List<UUID> listSourisDeplacees = new ArrayList<UUID>();

		// Loop sur chacune des souris et calcul du chemin
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].getMouse() != null) {
					// Vérification attente avant déplacement (herbe)...
					if (map[i][j].getMouse().getTime() > 1) {
						map[i][j].getMouse().setTime(map[i][j].getMouse().getTime()-1);
					} else {
						listCases = MapUtil.search(this, map[i][j], map[i][j].getMouse().getObjectif());

						// Si l'arrivée est bloquée, on essaie un autre chemin
						if (listCases == null) {
							listCases = map[i][j].getMouse().getInitialTarget();
							if (listCases != null && listCases.size() > 1 && map[listCases.get(1).getPos().getX()][listCases.get(1).getPos().getY()].getMouse() != null) {
								// Il y a déjà une autre souris ici, on tente de se rapprocher
								listCasesTemp = listCases;
								for (int k = listCasesTemp.size()-1; k > 0; k--) {
									listCases = MapUtil.search(this, map[i][j], listCasesTemp.get(k));
									if (listCases != null) {
										break; 
									}
								}
							}
						}
						// Fin calcul autre chemin

						// Recalcul de la porte la plus proche
						for (int k = 0; k < getCaseByType(ConfigUtil.getCharOf("arrival")).size(); k++) {
							listCasesTemp = MapUtil.search(this, map[i][j], getCaseByType(ConfigUtil.getCharOf("arrival")).get(k));
							if ( listCasesTemp != null && listCases != null && listCasesTemp.size() < listCases.size() ) {
								listCases = listCasesTemp;
							}
						}
						// Fin recalcul de la porte la plus proche

						if (listCases != null  && !listSourisDeplacees.contains(map[i][j].getMouse().getId())) { // On ajoute la souris à la map
							for (int x = 0; x < getCaseByType(ConfigUtil.getCharOf("arrival")).size(); x++) {
								if (listCases.get(0).getType() == ConfigUtil.getCharOf("arrival")) {
									map[i][j].unsetMouse();
									nb_deplacements_total++;
									souris_arrivees++;
									break;
								}
							}
							if (map[i][j].getMouse() != null) { // Souris pas encore arrivée
								map[listCases.get(0).getPos().getX()][listCases.get(0).getPos().getY()].setMouse(map[i][j].getMouse(), listCases );
								listSourisDeplacees.add(map[i][j].getMouse().getId());
								nb_deplacements_total++;
								map[i][j].unsetMouse();
							}
							
							
						} else if(listCases != null && listCases.size() == 1 && !listSourisDeplacees.contains(map[i][j].getMouse().getId())) {
							// Si l'un de ses voisins est une porte, alors C fini pour lui !
							
						} else {
							// Déplacement impossible
						}
					}	
				}
			}
		}

		for (int i = 0; i < getCaseByType(ConfigUtil.getCharOf("departure")).size(); i++) { // Loop départ
			start  = getCaseByType(ConfigUtil.getCharOf("departure")).get(i);
			
			for (int j = 0; j < start.getNb_souris_restant(); j++) { // Loop souris restantes
				if (getNeighbour(start).size() > 0) {
					Square point = getNeighbour(start).get(0);
					map[point.getPos().getX()][point.getPos().getY()].
						setMouse(new Minion(UUID.randomUUID()), start.getParcours_porte().get(start.getParcours_porte().size()-1), start.getParcours_porte());
					
					listSourisDeplacees.add(map[point.getPos().getX()][point.getPos().getY()].getMouse().getId());
					map[start.getPos().getX()][start.getPos().getY()].decrement_nb_souris_restant();
					souris_sorties++;
					nb_deplacements_total++;
				}
			}
		}

		tour++;
	}

	public int getNbSourisSurMap() {
		int res = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].getMouse() != null) {
					res ++;
				}
			}
		}
		return res;
	}

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public int getScore() {
		return souris_arrivees;
	}

	public int getNbDeplacementsTotal() {
		return nb_deplacements_total;
	}
}
