import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int V;
	static int max = 0;
	static int node = 0;
	static ArrayList<Node>[] list;
	static boolean visited[];
	
	// 1. 임의의 정점에서 가장 먼 노드(node)를 찾음
	// 2. node에서 가장 먼 노드까지의 거리가 트리의 지름
	// 즉 가장 먼 노드를 찾는 DFS를 두번 수행하면 됨 O(N)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		V = Integer.parseInt(br.readLine()); // 정점의 개수 V
		
		list = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			while(true) {
				int child_id = Integer.parseInt(st.nextToken());
				if(child_id == -1) break;
				int cost = Integer.parseInt(st.nextToken());
				list[id].add(new Node(child_id, cost));
			}
		}
		
		visited = new boolean[V+1];
		DFS(1, 0);
		
		visited = new boolean[V+1];
		DFS(node, 0);
		
		System.out.println(max);
	}
	

	static void DFS(int x, int len) {
		if(len > max) {
			max = len;
			node = x;
		}
		
		visited[x] = true;
		
		for(int i=0; i<list[x].size(); i++) {
			Node n = list[x].get(i);
			if(!visited[n.id]) {
				visited[n.id] = true;
				DFS(n.id, n.cost + len);
			}
		}
	}
	
	static class Node{
		private int id;
		private int cost;

		
		Node(int id, int cost){
			this.id = id;
			this.cost = cost;
		}
		
	}

}