import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		for(int i=1; i<=N; i++) Collections.sort(graph.get(i),Collections.reverseOrder());
		int[] visited = new int[N+1];
		int cnt = 1;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(R);
		visited[R] = cnt++;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int next : graph.get(now)) {
				if(visited[next] == 0) {
					queue.offer(next);
					visited[next] = cnt++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) sb.append(visited[i]).append('\n');
		System.out.println(sb);
	}
}
