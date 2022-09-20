import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, Q;
	static ArrayList<Link>[] graph;
	static int[][] usado;
	static final int INF = 2000000000;
	static boolean[] checked, visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			graph[p].add(new Link(q, r));
			graph[q].add(new Link(p, r));
		}
		
		usado = new int[N+1][N+1];
		for(int i=0; i<usado.length; i++) Arrays.fill(usado[i], INF);
		for(int i=0; i<usado.length; i++) usado[i][i] = 0;
		checked = new boolean[N+1];
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if(!checked[v]) BFS(v);
			int cnt = 0;

			for(int j=1; j<=N; j++) {
				if(usado[v][j] >= k) cnt++;
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
		
	}
	
	static class Link{
		private int q;
		private int r;
		
		Link(int q, int r){
			this.q = q;
			this.r = r;
		}
	}
	
	static void BFS(int v) {
		checked[v] = true;
		
		visited = new boolean[N+1];
		visited[v] = true;
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(v);
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(Link link : graph[now]) {
				int next = link.q;
				int usa = link.r;
				
				if(!visited[next]) {
					if(now == v) usado[v][next] = usa;
					else usado[v][next] = Math.min(usado[v][now], usa);
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
	}

}