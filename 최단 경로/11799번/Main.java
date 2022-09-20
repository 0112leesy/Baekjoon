import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		ArrayList<Node>[] graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[] dist = new int[n+1];
		Arrays.fill(dist, 100000000); // MaxValue로 했을 때 메모리초과
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		dist[S] = 0;
		pq.offer(new Node(S, dist[S]));
		
		int[] route = new int[n+1];

		while(!pq.isEmpty()) {
			Node now = pq.poll();

			if(now.to == E) {
				break;
			}
			
			if(dist[now.to] < now.cost) continue; // 다음도시의 dist가 다음도시로 가는 cost보다 적으면 continue

			for(Node next : graph[now.to]) {// [to, cost]
				if(dist[next.to] > dist[now.to] + next.cost) {
					dist[next.to] = dist[now.to] + next.cost;
					pq.offer(new Node(next.to, dist[next.to]));
					route[next.to] = now.to;
				}
			}
		}
		System.out.println(dist[E]);

		int cnt = 1;
		Stack<Integer> stack = new Stack<>();
		stack.push(E);
		while(route[E] != 0) {
			cnt++;
			stack.push(route[E]);
			E = route[E];
		}
		System.out.println(cnt);
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb);
	}
	
	static class Node implements Comparable<Node>{
		public int to, cost;
		
		Node(int to, int cost){
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return cost - o.cost;
		}
		
	}
}