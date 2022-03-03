import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, V;
	static boolean visited[];
	static StringBuilder sb = new StringBuilder();
	static ArrayList<ArrayList<Integer>> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		graph = new ArrayList<>();
		for(int i=0; i<=N; i++) graph.add(new ArrayList<Integer>());
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			graph.get(p).add(q);
			graph.get(q).add(p);
		}
		
		DFS(V);
		System.out.println(sb);
		
		BFS();
		System.out.println(sb);

	}
	
	static void DFS(int x) {
		visited[x] = true;
		sb.append(x).append(' ');
		
		Collections.sort(graph.get(x));
		for(int y : graph.get(x)) {
			if(!visited[y]) DFS(y);
		}
		
	}
	
	static void BFS() {
		visited = new boolean[N+1];
		sb = new StringBuilder();
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(V);
		visited[V] = true;
		
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			sb.append(temp).append(' ');
			
			for(int x : graph.get(temp)) {
				if(!visited[x]) {
					queue.offer(x);
					visited[x] = true;
				}
			}
		}
		
	}


}
