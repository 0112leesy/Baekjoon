import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	static int V;
	static Node[] nodes;
	static boolean visited[];
	static int[] max_dist_arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		V = Integer.parseInt(br.readLine()); // 정점의 개수 V
		
		nodes = new Node[V+1];
		for(int i=1; i<=V; i++) {
			nodes[i] = new Node(i);
		}
		
		for(int i=0; i<V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			while(true) {
				int child_id = Integer.parseInt(st.nextToken());
				if(child_id == -1) break;
				int child_dist = Integer.parseInt(st.nextToken());
				
				nodes[id].add_child(nodes[child_id], child_dist);
			}
		}
		
		max_dist_arr = new int[V+1];
		
		for(int i=1; i<=V; i++) {
			visited = new boolean[V+1];
			DFS(nodes[i], nodes[i]);
		}
		
		for(int i=1; i<=V; i++) {
			System.out.println(max_dist_arr[i]);
		}
	}
	

	static void DFS(Node root, Node node) {
		visited[node.id] = true;
		
		for(Node key_node : node.child_map.keySet()) {
			Node next_node = key_node;
			int dist = node.child_map.get(key_node);
			
			if(!visited[next_node.id]) {
				System.out.println("update > " + root.id + " : " + key_node.id);
				max_dist_arr[root.id] = Math.max(max_dist_arr[root.id], max_dist_arr[node.id] + dist);
				System.out.println(max_dist_arr[node.id]+" , "+dist);
				System.out.println(max_dist_arr[root.id]);
				DFS(root, key_node);
			}
		}
	}
	
	static class Node{
		private int id;
		// private Node parent;
		private HashMap<Node, Integer> child_map;
		
		Node(int id){
			this.id = id;
			child_map = new HashMap<>();
		}
		
		void add_child(Node child, int dist) {
			this.child_map.put(child, dist);
		}
	}

}