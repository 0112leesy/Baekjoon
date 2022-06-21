import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Integer[]>> graph = new ArrayList<>();
		for(int i=0; i<=V; i++) graph.add(new ArrayList<>());
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Integer[] {v, w}); // u -> v 간선의 정보 저장
		}
		
		int[] dist = new int[V+1];
		for(int i=0; i<=V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {

			@Override
			// {인덱스, 가중치} -> 가중치 기준으로 우선순위 비교
			public int compare(Integer[] o1, Integer[] o2) {
				return o1[1] - o2[1];
			}
			
		});
		pq.add(new Integer[] {K, 0});
		dist[K] = 0;
		
		while(!pq.isEmpty()) {
			Integer[] temp = pq.poll();
			int index = temp[0];
			
			for(Integer[] x : graph.get(index)) {
				if(dist[x[0]] > dist[index] + x[1]) {
					dist[x[0]] = dist[index] + x[1];
					pq.offer(new Integer[] {x[0], dist[x[0]]});
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=V; i++) {
			if(dist[i] == Integer.MAX_VALUE) sb.append("INF").append('\n');
			else sb.append(dist[i]).append('\n');
		}
		
		System.out.println(sb);
	
	}
	/*
	 * 1. 시작 노드에서 인접 노드 탐색
	 * 2. 인접 노드의 dist 값이 시작 노드의 dist 값 + 필요한 비용보다 크다면 변경
	 * 3. 변경이 이루어진 해당 노드를 우선순위큐에 추가
	 */
}
