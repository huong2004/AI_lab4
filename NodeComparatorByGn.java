package lab4;

import java.util.Comparator;

public class NodeComparatorByGn implements Comparator< Node> {

	public int compare(Node o1, Node o2) {
		Double g1 = o1.getH() + o1.getG();
		Double g2 = o2.getH() + o2.getG();
		int result = g1.compareTo(g2);
		if (result == 0)
			return o1.getLabel().compareTo(o2.getLabel());
		else
			return result;
	}


}
