import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
			for(int i=0; i<=V; i++) graph.add(new ArrayList<>());
			int[] visited = new int[V+1];
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int q = Integer.parseInt(st.nextToken());
				
				graph.get(p).add(q);
				graph.get(q).add(p);
			}
			
			Queue<Integer> queue = new LinkedList<>();
			
			boolean pos = true;
			for(int i=1; i<=V; i++) {
				// 연결되지 않은 여러 그래프로 이루어져 탐색이 완료되지 않을 수 있음
				// 모든 정점에 대하여 탐색이 가능하도록
				// 큐가 비었을 때, 방문하지 않은 정점을 찾아 큐에 넣는 과정 필요
				if(visited[i] == 0) {
					queue.offer(i);
					visited[i] = 1;
				}
				
				
				while(!queue.isEmpty()) {
					int x = queue.poll();
					
					for(int y : graph.get(x)) {
						if(visited[y] == 0) {
							queue.offer(y);
							visited[y] = 3 - visited[x];
						}
						else if(visited[y] == visited[x]) {
							pos = false;
							break;
						}
					}
				}
				
			}
			sb.append(pos? "YES" : "NO").append('\n');

		}
		System.out.println(sb);
	
	}

}
