import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	static int arr[][];
	static int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};
	static boolean visited[][];
	static int N, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		ArrayList<Integer> ans = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] == 1 && !visited[i][j]) {
					cnt = 0;
					dfs(i, j);
					ans.add(cnt);
				}
			}
		}
		
		Collections.sort(ans);
		StringBuilder sb = new StringBuilder();
		sb.append(ans.size()).append('\n');
		for(int n : ans) sb.append(n).append('\n');
		System.out.println(sb);
		
		
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		cnt ++;
		
		for(int i=0; i<4; i++) {
			int newx = x + dx[i];
			int newy = y + dy[i];
			
			if(newx < 0 || newx >= N || newy < 0 || newy >= N) continue;
			if(arr[newx][newy] == 1 && !visited[newx][newy]) dfs(newx, newy);
		}
		
	}

}