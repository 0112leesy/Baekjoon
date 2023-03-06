import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static int[][] type;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(getCount());
	}
	
	static int getCount() {
		type = new int[N+1][N+1];
		count = 0;
		
		type[1][2] = 1;
		DFS(1, 2);
		return count;
		
	}
	
	// type - 1:가로, 2:세로, 3:대각선
	
	static void DFS(int x, int y) {
		if(x == N && y == N) {
			count += 1;
			return;
		}
		
		if(type[x][y] == 1) { // 현재 가로로 놓여있을 때
			if(rightPossible(x, y)) { // 오른쪽으로 밀 수 있을 때
				type[x][y+1] = 1;
				DFS(x, y+1);
			}
			if(diagPossible(x, y)) {
				// 대각선으로 밀 수 있을 때
				type[x+1][y+1] = 3;
				DFS(x+1, y+1);
			}
		}
		else if(type[x][y] == 2) { // 현재 세로로 놓여있을 때
			if(downPossible(x, y)) { // 아래로 밀 수 있을 때
				type[x+1][y] = 2;
				DFS(x+1, y);
			}
			if(diagPossible(x, y)) {
				// 대각선으로 밀 수 있을 때
				type[x+1][y+1] = 3;
				DFS(x+1, y+1);
			}
		}
		else if(type[x][y] == 3) { // 현재 대각선으로 놓여있을 때
			if(rightPossible(x, y)) { // 오른쪽으로 밀 수 있을 때
				type[x][y+1] = 1;
				DFS(x, y+1);
			}
			if(downPossible(x, y)) { // 아래로 밀 수 있을 때
				type[x+1][y] = 2;
				DFS(x+1, y);
			}
			if(diagPossible(x, y)) {
				// 대각선으로 밀 수 있을 때
				type[x+1][y+1] = 3;
				DFS(x+1, y+1);
			}
		}
	}
	
	static boolean rightPossible(int x, int y) {
		if(y+1 <= N && map[x][y+1] == 0) { // 오른쪽으로 밀 수 있을 때
			return true;
		}
		return false;
	}
	
	static boolean downPossible(int x, int y) {
		if(x+1 <= N && map[x+1][y] == 0) { // 아래로 밀 수 있을 때
			return true;
		}
		return false;
	}
	
	static boolean diagPossible(int x, int y) {
		if(x+1 <= N && y+1 <= N && map[x][y+1] == 0 && map[x+1][y] == 0 && map[x+1][y+1] == 0) {
			// 대각선으로 밀 수 있을 때
			return true;
		}
		return false;
	}
	
}