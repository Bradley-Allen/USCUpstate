/**
 * DFS
 */

import java.util.*;

public class DFSGraph {
	private LinkedList<Integer> adjLists[];
	private boolean visited[];

	// Graph creation
	DFSGraph(int vertices) {
		adjLists = new LinkedList[vertices];
		visited = new boolean[vertices];

		for (int i = 0; i < vertices; i++)
			adjLists[i] = new LinkedList<Integer>();
	}

	// Add edges
	void addEdge(int vertex, int edgeTo) {
		adjLists[vertex].add(edgeTo);
	}

	// DFS algorithm
	void DFS(int vertex) {
		visited[vertex] = true;
		System.out.print(vertex + " ");

		Iterator<Integer> iterate = adjLists[vertex].listIterator();
		while (iterate.hasNext()) {
			int adj = iterate.next();
			if (!visited[adj])
				DFS(adj);
		}
	}
}