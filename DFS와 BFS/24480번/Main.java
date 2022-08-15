import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,R,cnt;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
		visited = new int[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		for(int i=1; i<=N; i++) Collections.sort(graph.get(i), Collections.reverseOrder());
		cnt = 1;
		DFS(R);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) sb.append(visited[i]).append('\n');
		System.out.println(sb);
	}
	
	static void DFS(int x) {
		visited[x] = cnt++;
		for(int y : graph.get(x)) {
			if(visited[y] == 0) {
				DFS(y);
			}
		}
	}

}
