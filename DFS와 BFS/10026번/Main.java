import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main { // DFS로 더 간단하게 풀어보자
	
	static int N, cnt;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	static int[][] normal, abnormal;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		normal = new int[N][N];
		abnormal = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				char c = str.charAt(j);
				if(c == 'R') {
					normal[i][j] = 1;
					abnormal[i][j] = 1;
				}
				if(c == 'G') {
					normal[i][j] = 2;
					abnormal[i][j] = 1;
				}
				if(c == 'B') {
					normal[i][j] = 3;
					abnormal[i][j] = 3;
				}
			}
		}

		visited = new boolean[N][N];
		
		// 정상, 비정상 모두 R, G, B 각각에 대해 BFS floor fill을 한다
		int normal_total = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				BFS(normal, 1, i, j);
				BFS(normal, 2, i, j);
				BFS(normal, 3, i, j);
			}
		}
		normal_total = cnt;
		
		cnt = 0;
		visited = new boolean[N][N];
		int abnormal_total = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				BFS(abnormal, 1, i, j);
				BFS(abnormal, 3, i, j);
			}
		}
		abnormal_total = cnt;
		
		
		System.out.println(normal_total+" "+abnormal_total);
		
	}
	
	static void BFS(int[][] arr, int color, int n, int m) {
		
		if(!visited[n][m] && arr[n][m] == color) {
			cnt++;
			Queue<Integer[]> queue = new LinkedList<>();
			
			queue.offer(new Integer[] {n, m});
			visited[n][m] = true;
			
			while(!queue.isEmpty()) {
				Integer[] temp = queue.poll();
				int x = temp[0];
				int y = temp[1];
				for(int i=0; i<4; i++) {
					int newx = x + dx[i];
					int newy = y + dy[i];
					if(newx < 0 || newy < 0 || newx >= N || newy >= N) continue;
					if(!visited[newx][newy] && arr[newx][newy] == color) {
						queue.offer(new Integer[] {newx, newy});
						visited[newx][newy] = true;
					}
				}
			}
		}
	}

}
