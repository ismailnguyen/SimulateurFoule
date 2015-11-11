/**
 * Simulateur de foule
 * 
 * Kevin DHORNE, Fabien GAMELIN, Ismaïl NGUYEN
 * 
 * ESGI 2015/2016
 */

package simulation.algorithm;

import java.util.Comparator;

public class AStarComparator implements Comparator<AStar> {

	public int compare(AStar c1, AStar c2) {

		int e1 = c1.getHeuristic();
		int e2 = c2.getHeuristic();

		int c = e1 - e2;

		if (c > 0)
			return -1;
		else if (c < 0)
			return 1;
		else
			return 0;
	}
}
