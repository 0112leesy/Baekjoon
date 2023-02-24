import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] arr;
	static int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};
	static boolean[][] visited;
	static int maxScore;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    
	    arr = new int[N][M];
	    for(int i=0; i<N; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<M; j++) {
	    		arr[i][j] = Integer.parseInt(st.nextToken());
	    	}
	    }
	    
	    visited = new boolean[N][M];
	    maxScore = 0;
	    
	    for(int i=0; i<N; i++) {
	    	for(int j=0; j<M; j++) {
	    		visited[i][j] = true;
	    		dfs(i, j, 1, arr[i][j]);
	    		visited[i][j] = false;
	    	}
	    }
		
	    System.out.println(maxScore);
	}
	
	static void dfs(int x, int y, int level, int score) {
		
		if(level == 4) {
			maxScore = Math.max(maxScore, score);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int newx = x + dx[i];
			int newy = y + dy[i];
			
			if(newx < 0 || newx >= N || newy < 0 || newy >= M) continue;
			
			if(!visited[newx][newy]) {
				
				if(level == 2) { // ㅜ 모양 블럭 처리 (newx, newy)를 방문했다고 가정하고 dfs 수행
					visited[newx][newy] = true;
					dfs(x, y, level+1, score + arr[newx][newy]);
					visited[newx][newy] = false;
				}
				
				visited[newx][newy] = true;
				dfs(newx, newy, level+1, score + arr[newx][newy]);
				visited[newx][newy] = false;
			}
		}
	}
}