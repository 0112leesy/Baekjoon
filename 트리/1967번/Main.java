import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int nodeNumber;
	static ArrayList<ArrayList<Edge>> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		nodeNumber = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<>();
		for(int i=0; i<=nodeNumber; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<nodeNumber-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int fromNode = Integer.parseInt(st.nextToken());
			int toNode = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(fromNode).add(new Edge(toNode, weight));	
			graph.get(toNode).add(new Edge(fromNode, weight));
		}
		
		visited = new boolean[nodeNumber+1];
		visited[1] = true;
		DFS(1, 0);
		
		visited = new boolean[nodeNumber+1];
		visited[farNode] = true;
		DFS(farNode, 0);
		
		System.out.println(farWeight);
	}
	
	static class Edge{
		int node;
		int weight;
		
		Edge(int node, int weight){
			this.node = node;
			this.weight = weight;
		}
	}
	
	static int farNode = 0;
	static int farWeight = 0;
	static boolean visited[];
	
	static void DFS(int currentNode, int currentWeightSum) {
		if(currentWeightSum > farWeight) {
			farWeight = currentWeightSum;
			farNode = currentNode;
		}

		for(Edge edge : graph.get(currentNode)) {
			int nextNode = edge.node;
			int weight = edge.weight;
			
			if(!visited[nextNode]) {
				visited[nextNode] = true;
				DFS(nextNode, currentWeightSum + weight);
			}
			
		}
	}
	
}