import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
		// 길을 건너지 않고 소를 만나는 경우를 세서 빼줌,

		static int N, K, R, ans;
		static int[][] map;
		static ArrayList<Node>[][] bridge;
		static Node[] cows;
		static int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		bridge = new ArrayList[N+1][N+1];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) bridge[i][j] = new ArrayList<Node>();
		}
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			int fromx = Integer.parseInt(st.nextToken());
			int fromy = Integer.parseInt(st.nextToken());
			int tox = Integer.parseInt(st.nextToken());
			int toy = Integer.parseInt(st.nextToken());
			bridge[fromx][fromy].add(new Node(tox, toy));
			bridge[tox][toy].add(new Node(fromx, fromy));
		}
		
		map = new int[N+1][N+1];
		cows = new Node[K+1];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = i+1;
			cows[i+1] = new Node(x, y);
		}
		
		System.out.println(Solution());
	}
	
	static int Solution() {
		int ans = 0;
		boolean[][] connected = new boolean[K+1][K+1];
		
		for(int i=1; i<=K; i++) {
			boolean[][] visited = new boolean[N+1][N+1];
			Queue<Node> queue = new LinkedList<>();
			
			Node current_cow = cows[i];
			queue.offer(current_cow);
			visited[current_cow.x][current_cow.y] = true;
			
			while(!queue.isEmpty()) {
				Node now = queue.poll();
				
				if(map[now.x][now.y] != 0) connected[i][map[now.x][now.y]] = true;
				
				for(int j=0; j<4; j++) {
					int nextx = now.x+dx[j];
					int nexty = now.y+dy[j];
					
					if(nextx < 1 || nextx > N || nexty < 1 || nexty > N || visited[nextx][nexty]) continue;
					if(bridge[now.x][now.y].contains(new Node(nextx, nexty))) continue;
					
					queue.offer(new Node(nextx, nexty));
					visited[nextx][nexty] = true;
				}
			}
			
			for(int k=i+1; k<=K; k++) {
				if(!connected[i][k]) ans++;
			}
		}
		
		return ans;
	}
	
	static class Node{

		private int x;
		private int y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object obj) { // contains() 함수 내부에서 equals() 함수를 사용하므로 오버라이딩 필요
			Node node = (Node) obj;
			return x == node.x && y == node.y;
		}
	}
}