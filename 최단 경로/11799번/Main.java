import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Integer[]>> graph = new ArrayList<>();
		for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Integer[] {to, cost});
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
			// 거리 기준으로 오름차순
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}
			
		});
		
		dist[S] = 0;
		pq.offer(new Integer[] {S, dist[S]});
		
		while(!pq.isEmpty()) {
			Integer[] tmp = pq.poll();
			int now = tmp[0];
			if(now == E) {
				break;
			}
			int now_dist = tmp[1];
			for(Integer[] e : graph.get(now)) {// [to, cost]
				if(dist[e[0]] > dist[now] + e[1]) {
					dist[e[0]] = dist[now] + e[1];
					pq.offer(new Integer[] {e[0], dist[e[0]]});
				}
			}
		}
		System.out.println(dist[E]);
	}
}