import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] is_cheese;
	static int N, M;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		is_cheese = new int[N+1][M+1];
		
		int total_cheese = 0;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				is_cheese[i][j] = Integer.parseInt(st.nextToken());
				if(is_cheese[i][j] == 1) total_cheese++;
			}
		}
		
		// 치즈별로 외부와 인접한 변의 수을 구할 때 BFS floor fill 사용
		// is_cheese가 0일 때만 탐색, 인접한 1들의 값 ++;
		int cnt = 0;
		while(total_cheese > 0) {// 녹지 않은 치즈가 있을 때
			cnt++; // 시간 카운트
			visited = new boolean[N+1][M+1];
			Queue<Integer[]> queue = new LinkedList<>();
			queue.offer(new Integer[] {0,0});
			visited[0][0] = true;
			
			// 1. 외부와 2변 이상 인접한 치즈의 갯수 구하기
			while(!queue.isEmpty()) {
				Integer[] temp = queue.poll();
				int x = temp[0];
				int y = temp[1];
				
				for(int i=0; i<4; i++) {
					int newx = x + dx[i];
					int newy = y + dy[i];
					
					if(newx < 0 || newy < 0 || newx > N || newy > M) continue;
					
					if(is_cheese[newx][newy] >= 1) is_cheese[newx][newy]++;
					if(!visited[newx][newy] && is_cheese[newx][newy] == 0) {
						queue.offer(new Integer[] {newx, newy});
						visited[newx][newy] = true;
					}
				}
			}
			
			// 2. 치즈 녹이기
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(is_cheese[i][j] >= 3) {
						is_cheese[i][j] = 0;
						total_cheese--;
					}
					else if(is_cheese[i][j] == 2) is_cheese[i][j] = 1;
				}
			}			
		}
		
		System.out.println(cnt);
		
	}

}

