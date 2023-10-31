package lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByGn());

		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node node = frontier.poll(); // Chooses the lowest-cost node in frontier
			if (node.getLabel().equals(goal))
				return node;
			else {
				explored.add(node);
				List<Edge> children = node.getChildren();
				for (Edge child : children) {
					Node n = child.getEnd();
					if (!frontier.contains(n) && !explored.contains(n)) {
						n.setParent(node);
						n.setG(n.getG() + child.getWeight());
						frontier.add(n);

					} else if (frontier.contains(n) && n.getG() > node.getG() + child.getWeight()) {
						frontier.remove(n);
						n.setG(node.getG() + child.getWeight());
						n.setParent(node);
						frontier.add(n);

					}
				}

			}
		}
		return null;

	}
//	public boolean isAdmissibleH(Node root, String goal) {
//	//h*= PathCost 
//	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparatorByGn());
		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node node = frontier.poll();

			if (node.getLabel().equals(goal)) {
				return node; // Goal reached
			}

			explored.add(node);

			// Generate successor states and add them to the open set
			List<Edge> children = node.getChildren();
			for (Edge child : children) {
				Node n = child.getEnd();

				if (!frontier.contains(n) && !explored.contains(n)) {

					n.setParent(node);
					n.setG(n.getG() + child.getWeight());
					frontier.add(n);

				} else if (frontier.contains(n) && n.getG() > node.getG() + child.getWeight()) {
					frontier.remove(n);
					n.setG(node.getG() + child.getWeight());
					n.setParent(node);
					frontier.add(n);
				}
			}
		}
		return null; // No path found

	}
}
