/**
 * BFS
 */

import java.util.*;

public class BFSGraph {
	private int Value;
	private LinkedList<Integer> adj[];

	// Create a graph
	BFSGraph(int value) {
		Value = value;
		adj = new LinkedList[value];
		for (int i = 0; i < value; ++i)
			adj[i] = new LinkedList();
		}

	// Add edges to the graph
	void addEdge(int vertex, int edgeTo) {
		adj[vertex].add(edgeTo);
	}

	// BFS algorithm
	void BFS(int startingVertex) {

		boolean visited[] = new boolean[Value];

		LinkedList<Integer> queue = new LinkedList<>();

		visited[startingVertex] = true;
		queue.add(startingVertex);

		while (queue.size() != 0) {
			startingVertex = queue.poll();
			System.out.print(startingVertex + " ");

			Iterator<Integer> i = adj[startingVertex].listIterator();
			
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}
}