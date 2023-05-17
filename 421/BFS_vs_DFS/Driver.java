/**
 * Driver class, builds graphs based on matrix and prints
 * after DFS and BFS algorithms.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	 public static void main(String args[]) {
		  Scanner scan = new Scanner(System.in);
		  String filename = scan.next();
		  doDFS(filename);
		  System.out.println();
		  doBFS(filename);
		  scan.close();
	  }
	 
	 private static void doDFS(String filename) {
			try {
				// Opens file and reads
				File file = new File(filename);
				Scanner filescan = new Scanner(file);
				
				// Reads first line, containing the amount of vertices
				int vertexCount = Integer.parseInt(filescan.nextLine());
				
				// Creates directed graph with given amount of vertices
				DFSGraph dfsg = new DFSGraph(vertexCount);
				
				// Loops through each vertex
				for (int i = 0; i < vertexCount; ++i) {
					// Loops through each vertex's adjacency
					for (int j = 0; j < vertexCount; ++j) {
						int value = filescan.nextInt();
						if (value == 1) {
							dfsg.addEdge(i, j);
						}
					}
				}
				
				// Styles printing
				System.out.print("DFS: ");
				
				// Starts at vertex 0, does DSF algorithm
				dfsg.DFS(0);
				
				// Close scanner for resources
				filescan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	 
	 private static void doBFS(String filename) {
			try {
				// Opens file and reads
				File file = new File(filename);
				Scanner filescan = new Scanner(file);
				
				// Reads first line, containing the amount of vertices
				int vertexCount = Integer.parseInt(filescan.nextLine());
				
				// Creates directed graph with given amount of vertices
				BFSGraph bfsg = new BFSGraph(vertexCount);
				
				// Loops through each vertex
				for (int i = 0; i < vertexCount; ++i) {
					// Loops through each vertex's adjacency
					for (int j = 0; j < vertexCount; ++j) {
						int value = filescan.nextInt();
						if (value == 1) {
							bfsg.addEdge(i, j);
						}
					}
				}
				
				// Styles printing
				System.out.print("BFS: ");
				bfsg.BFS(0);
				
				// Close scanner for resources
				filescan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
}
