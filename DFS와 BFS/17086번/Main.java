import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] isShark;
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isShark = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				isShark[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = -1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(isShark[i][j] == 0) {
					int safeDistance = getSafeDistance(i, j);
					max = Math.max(max, safeDistance);
				}
			}
		}
		
		System.out.println(max);
	}
	
	static int getSafeDistance(int x, int y) {
		int[][] visit = new int[N][M];
		Queue<Integer[]> queue = new LinkedList<>();
		
		visit[x][y] = 1;
		queue.offer(new Integer[] {x, y});
		
		while(!queue.isEmpty()) {
			Integer[] tmp = queue.poll();
			int nowx = tmp[0];
			int nowy = tmp[1];
			
			for(int i=0; i<8; i++) {
				int nextx = nowx + dx[i];
				int nexty = nowy + dy[i];
				
				if(nextx < 0 || nextx >= N || nexty < 0 || nexty >= M || visit[nextx][nexty] > 0) continue;
				if(isShark[nextx][nexty] == 1) {
					return visit[nowx][nowy];
				}
				else {
					visit[nextx][nexty] = visit[nowx][nowy] + 1;
					queue.offer(new Integer[] {nextx, nexty});
				}
			}
		}
		
		return -1;
	}
	
}