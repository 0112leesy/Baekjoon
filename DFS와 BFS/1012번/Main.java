import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int arr[][];
	static int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};
	static boolean visited[][];
	static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				arr[x][y] = 1;
			}
			
			int ans = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						ans++;
					}
				}
			}
			sb.append(ans).append('\n');	
		}
		
		System.out.println(sb);
		
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int newx = x + dx[i];
			int newy = y + dy[i];
			
			if(newx < 0 || newx >= N || newy < 0 || newy >= M) continue;
			if(arr[newx][newy] == 1 && !visited[newx][newy]) dfs(newx, newy);
		}
		
	}

}