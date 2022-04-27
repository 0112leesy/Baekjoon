import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 플로이드 와셜 알고리즘으로 풀이 시 시간복잡도가 O(V^3)로 시간초과가 남
	// 따라서 다익스트라 알고리즘으로 풀이함; 시간복잡도 O((V+E)logV)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생(마을) 수
		int M = Integer.parseInt(st.nextToken()); // 도로 수
		int X = Integer.parseInt(st.nextToken()); // 파티 장소
		
		ArrayList<ArrayList<Integer[]>> graph = new ArrayList<>();
		for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			// 연결 리스트에 {도착지, 소요시간} 정보 저장
			graph.get(A).add(new Integer[] {B, T});
		}
		
		PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
			public int compare(Integer[] o1, Integer[] o2) {
				// 소요시간 기준의 우선순위큐 생성 -> 작을수록 우선순위 높음
				return o1[1] - o2[1];
			};
		});
		
		int[][] dist = new int[N+1][N+1];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			} // i에서 j로 가는 최소 거리 저장
		}
		
		// 학생 i 별로 각 마을까지의 최소 거리를 다익스트라 알고리즘을 통해 구함
		for(int i=1; i<=N; i++) {
			pq.offer(new Integer[] {i, 0}); // 마을 i에서 탐색 시작
			dist[i][i] = 0; // i에서 i로 가는 거리는 0
			
			while(!pq.isEmpty()) {
				Integer[] now = pq.poll();
				int now_city = now[0];
				
				// 인접한 마을들에 대해 최소 거리 값이 갱신되는지 확인함
				for(Integer[] next : graph.get(now_city)) {
					int next_city = next[0];
					int weight = next[1];
					
					if(dist[i][next_city] > dist[i][now_city] + weight) {
						dist[i][next_city] = dist[i][now_city] + weight;
						pq.offer(next);
					} // 최소 거리 값이 갱신되면 해당 마을을 우선순위 큐에 넣음
				}
			}
		}

		int max = 0;
		// 마을 i에서 X로 가는 거리 + 마을 X에서 i로 가는 거리의 최댓값을 구해줌
		for(int i=1; i<=N; i++) {
			int sum_dist = dist[i][X] + dist[X][i];
			max = Math.max(max, sum_dist);
		}
		
		System.out.println(max);
		
	}

}

