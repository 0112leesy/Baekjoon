import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 벨만-포드 알고리즘 : 한 정점에서 다른 모든 정점까지의 최단 경로를 구함
	 * 특징) 간선에 음의 가중치가 있을 때 사용
	 * 시간복잡도) O(VE); 정점 V개, 간선 E개		// 다익스트라: O(ElogV)
	 * 1. 최단경로는 V-1개의 정점을 지나므로 모든 간선에 대해 V-1번 반복하여 최단경로 업데이트
	 * 2. D[s] != INF && D[s]+W[s,e] < D[e] 일때 D[e] 업데이트
	 * 3. 추가로 1회의 반복을 더 돌았을 때 최단경로가 바뀌면 음수 사이클이 존재하는 것임
	 */

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> edge_info = new ArrayList<>();
			edge_info.add(A);
			edge_info.add(B);
			edge_info.add(C);
			
			edges.add(edge_info);
		}
		
		final int INF = 5000000;
		long[] dist = new long[N+1]; // 500 * 6000 * -10000 일떄 underflow 발생 -> long 배열로 지정해야함
		Arrays.fill(dist, INF);
		dist[1] = 0;
		
		boolean neg_cycle = false;
		for(int i=0; i<N; i++) {
			for(ArrayList<Integer> edge : edges) {
				int start = edge.get(0);
				int end = edge.get(1);
				int weight = edge.get(2);
				
				if(dist[start] != INF && dist[start] + weight < dist[end]) {
					dist[end] = dist[start] + weight;
					// N회차에 최단거리가 업데이트되면 음수 사이클이 있는 것임
					if(i == N-1) neg_cycle = true;
				}
			}
		}
		
		if(neg_cycle) System.out.println(-1);
		else {
			StringBuilder sb = new StringBuilder();
			for(int i=2; i<=N; i++) {
				if(dist[i] == INF) sb.append(-1);
				else sb.append(dist[i]);
				sb.append('\n');
			}
			System.out.println(sb);
		}
	}
}
