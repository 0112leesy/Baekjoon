import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static int[][] arr, dist_start, dist_end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M+1];
		dist_start = new int[N+1][M+1];
		dist_end = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			String str = br.readLine();
			for(int j=1; j<=M; j++) {
				arr[i][j] = str.charAt(j-1) - '0';
			}
		}
		
		// BFS 1 : (1,1)에서부터의 최단 거리 계산
		Queue<Integer []> queue_start = new LinkedList<>();
		queue_start.offer(new Integer[] {1, 1});
		dist_start[1][1] = 1;
		
		while(!queue_start.isEmpty()) {
			Integer[] temp = queue_start.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int i=0; i<4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];
				
				if(newx < 1 || newx > N || newy < 1 || newy > M) continue;
				if(dist_start[newx][newy] == 0) {
					dist_start[newx][newy] = dist_start[x][y] + 1;
					if(arr[newx][newy] == 0) {
						queue_start.offer(new Integer[] {newx, newy});
					}
				}
			}
		}
		
		// BFS 2 : (N,M)에서부터의 최단 거리 계산
		Queue<Integer []> queue_end = new LinkedList<>();
		queue_end.offer(new Integer[] {N, M});
		dist_end[N][M] = 1;
		
		while(!queue_end.isEmpty()) {
			Integer[] temp = queue_end.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int i=0; i<4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];
				
				if(newx < 1 || newx > N || newy < 1 || newy > M) continue;
				if(dist_end[newx][newy] == 0) {
					dist_end[newx][newy] = dist_end[x][y] + 1;
					if(arr[newx][newy] == 0) {
						queue_end.offer(new Integer[] {newx, newy});
					}
				}
			}
		}
		
		// (x,y)지점의 벽을 부숴 이동했을 때의 최단거리는
		// dist_start[x][y] + dist_end[x][y]
		// 따라서 위의 값 중 최솟값을 찾으면 됨
		int min = Integer.MAX_VALUE;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(dist_start[i][j] == 0 || dist_end[i][j] == 0) continue;
				int dist_total = dist_start[i][j] + dist_end[i][j] - 1;
				min = Math.min(min, dist_total);
			}
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	
	}

}
