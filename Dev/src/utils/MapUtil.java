/**
 * Simulateur de foule
 * 
 * Kevin DHORNE, Fabien GAMELIN, Ismaïl NGUYEN
 * 
 * ESGI 2015/2016
 */

package utils;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import simulation.algorithm.AStar;
import simulation.algorithm.AStarComparator;
import simulation.queue.LinkSimple;
import simulation.queue.LinkedPriorityQueue;
import ui.model.Ground;
import ui.model.SpecialPoint;
import ui.model.Square;
import algo.linkedlist.nodes.interfaces.ILinkSimple;

public class MapUtil {

	public static List<Square> search(Ground map, Square source, Square target) {

		HashMap<String, AStar> openSet = new HashMap<String, AStar>();
		HashMap<String, AStar> closeSet = new HashMap<String, AStar>();
		AStarComparator comparator = new AStarComparator();
		LinkedPriorityQueue<AStar> queue = new LinkedPriorityQueue<AStar>(
				comparator);
		AStar start = new AStar(source, 0, map.calcLength(source,
				target));

		openSet.put(source.getPos().toString(), start);
		queue.add(start);

		AStar goal = null;
		while (openSet.size() > 0) {
			AStar x = queue.remove();
			openSet.remove(x.getCurrent().getPos().toString());
			if (x.getCurrent().getPos().toString()
					.equals(target.getPos().toString())) {
				// System.out.println("Un chemin est possible entre le départ et l'arrivée.");
				goal = x;
				break;
			} else {
				// System.out.println("Recherche de l'arrivée en cours ...");
				closeSet.put(x.getCurrent().getPos().toString(), x);
				List<Square> neighbours = map.getNeighbour(x.getCurrent());

				for (int i = 0; i < neighbours.size(); i++) {
					SpecialPoint pos = neighbours.get(i).getPos();
					AStar visited = closeSet.get(pos.toString());

					if (visited == null) {
						int g = x.getDistance()
								+ map.calcLength(x.getCurrent(),
										neighbours.get(i));
						AStar n = openSet.get(pos.toString());

						if (n == null) {
							// not in the open set
							n = new AStar(neighbours.get(i), g,
									map.calcLength(neighbours.get(i), target));
							n.setPrevious(x);
							openSet.put(neighbours.get(i).getPos().toString(),
									n);
							queue.add(n);
						} else if (g < n.getDistance()) {
							n.setPrevious(x);
							n.setDistance(g);
							n.setHeuristic(map.calcLength(neighbours.get(i),
									target));
						}
					}
				}
			}
		}

		// after found the target, start to construct the path
		if (goal != null) {
			List<Square> list = new ArrayList<Square>();
			ILinkSimple<Square> stack = new LinkSimple<Square>();
			stack.setValue(goal.getCurrent());
			AStar parent = goal.getPrevious();
			while (parent != null) {
				LinkSimple<Square> stackNext = new LinkSimple<Square>();
				stackNext.setValue(parent.getCurrent());
				stackNext.setNext(stack);
				stack = stackNext;
				parent = parent.getPrevious();
			}
			while (stack.getNext() != null) {
				stack = stack.getNext();
				list.add(stack.getValue());
			}
			return list;
		}

		return null;
	}
}
