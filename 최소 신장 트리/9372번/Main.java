import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			for(int i=0; i<=N; i++) graph.add(new ArrayList<Integer>());
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				graph.get(b).add(a);
			}
			
			visited = new boolean[N+1];
			cnt = 0;
			dfs(1);
			sb.append(cnt-1).append('\n');
		}
		System.out.println(sb);	
	}
	
	static void dfs(int x) {
		visited[x] = true;
		cnt++;
		
		for(int y : graph.get(x)) {
			if(!visited[y]) {
				dfs(y);
			}
		}
	}	
}

