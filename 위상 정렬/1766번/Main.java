import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] indegree;
	static ArrayList<ArrayList<Integer>> graph;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException { // 난이도를 고려하기 위해 우선순위큐를 활용하여 풀이함
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N+1];
		graph = new ArrayList<>();
		for(int i=0; i<=N; i++) graph.add(new ArrayList<Integer>());
		pq = new PriorityQueue<>();

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			indegree[q]++;
			graph.get(p).add(q);
		}
		
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) pq.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			int current = pq.poll();
			sb.append(current).append(' ');
			for(int next : graph.get(current)) {
				indegree[next]--;
				if(indegree[next] == 0) {
					pq.offer(next);
				}
			}
		}
		
		System.out.println(sb);
		
	}
	
}
