package lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparotorByH());
		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node node = frontier.poll(); // Chooses the lowest-cost node in frontier
			if (node.getLabel() == goal)
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

					}
				}

			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparotorByH());
		List<Node> explore = new ArrayList<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node check = frontier.poll();
			if (!check.getLabel().equals(start)) {
				for (Node c : check.getChildrenNodes()) {
					frontier.add(c);
				}
			} else {
				frontier.clear();
				frontier.add(check);

				while (!frontier.isEmpty()) {

					Node current = frontier.poll();
					System.out.println("Current: " + current.getLabel());
					if (current.getLabel().equals(goal)) {
						return current;
					} else {
						explore.add(current);
						for (Node n : current.getChildrenNodes()) {
							if ((!frontier.contains(n) && (!explore.contains(n)))) {
								n.setParent(current);
								for (Edge i : n.getParent().getChildren()) {
									if (i.getEnd().getLabel().equals(n.getLabel())) {
										n.setG(i.getWeight() + n.getParent().getG());
									}
								}

								frontier.add(n);
								System.out.println("add: " + n.getLabel());

							} else {
								Node parentBefore = n.getParent();
								double before = n.getG();
								n.setParent(current);
								for (Edge i : n.getParent().getChildren()) {
									if (i.getEnd().getLabel().equals(n.getLabel())) {
										n.setG(i.getWeight() + n.getParent().getG());
									}
								}

								double after = n.getG();
								if (after >= before) {
									n.setParent(parentBefore);
									n.setG(before);
								}

							}

						}

					}
					System.out.println(frontier.toString());
					System.out.println("------------\n");
				}
			}
		}

		System.out.println("khong tim thay duong di trong");
		return null;

	}

}
