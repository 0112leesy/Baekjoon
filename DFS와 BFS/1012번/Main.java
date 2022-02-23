import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int m;
	static int n;
	static int k; 
	static int[][] maps;
	static boolean[][] visited;
	static int result; 
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0}; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int t = Integer.parseInt(str);
		
		for(int i=0; i<t; i++) {
			str = br.readLine();
			m = Integer.parseInt(str.split(" ")[0]);
			n = Integer.parseInt(str.split(" ")[1]);
			k = Integer.parseInt(str.split(" ")[2]);
			
			maps = new int[n][m];
			visited = new boolean[n][m];
			result = 0;

			int x;
			int y;
			for(int j=0; j<k; j++) {
				str = br.readLine();
				x = Integer.parseInt(str.split(" ")[0]);
				y = Integer.parseInt(str.split(" ")[1]);
				maps[y][x] = 1;
			}
			
			for(int a=0; a<n; a++) {
				for(int b=0; b<m; b++) {
					if(maps[a][b]==1 && !visited[a][b]) {
						result++;
						visited[a][b] = true;
						dfs(a, b);
					}
				}
			}

			System.out.println(result);
		}
	}
	
	static void dfs(int y, int x) {
		int nx, ny;
		
		for(int i=0; i<4; i++) {
			ny = y + dy[i];
			nx = x + dx[i];
			
			// 범위 체크
			if(ny>=0 && nx>=0 && ny<n && nx<m) {
				// 배추가 있고 방문 안한 곳
				if(maps[ny][nx]==1 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					dfs(ny, nx);
				}				
			}
		}
	}

}