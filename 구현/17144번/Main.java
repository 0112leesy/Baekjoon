import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/* u, l에 공기청정기 있을 때
	 * 반시계회전) u행에서 하나씩 오른쪽으로 이동, C-2가 C-1이 될때까지
	 * 			C-1열에서는 하나씩 위로 이동, 1행이 0행이 될때까지
	 * 			0행에서는 하나씩 왼쪽으로 이동, 1열이 0열이 될때까지 ..
	 * 한 변씩 이동을 진행하였음
	 * 
	 * 확산이 일어날 때 서로에게 영향을 받기 때문에 확산을 전체적으로 진행 후
	 * 최종적으로 다같이 더해주어야 함
	 */
	
	static int R, C, T;
	static int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};
	static int[][] map, moved_map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		moved_map = new int[R+1][C+1];
		
		int l = 0;
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) l = i;
			}
		}
		int u = l-1;
		
		int time = 0;
		while(time < T) {
			spread();
			for(int i=1; i<=R; i++) {
				for(int j=1; j<=C; j++) {
					if(moved_map[i][j] > 0) {
						map[i][j] += moved_map[i][j];
						moved_map[i][j] = 0;
					}
				}
			}
			upper_wind(u);
			lower_wind(l);
			time++;
		}
		
		long total = 0;
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j]>0) total += map[i][j];
			}
		}
		System.out.println(total);
		
	}
	
	static void upper_wind(int u) {
		int tmp = map[u][C];
		for(int i=C; i>2; i--) {
			map[u][i] = map[u][i-1];
		}
		map[u][2] = 0;
		int tmp2 = map[1][C];
		for(int i=1; i<u-1; i++) {
			map[i][C] = map[i+1][C];
		}
		map[u-1][C] = tmp;
		int tmp3 = map[1][1];
		for(int i=1; i<C-1; i++) {
			map[1][i] = map[1][i+1];
		}
		map[1][C-1] = tmp2;
		for(int i=u; i>2; i--) {
			map[i][1] = map[i-1][1];
		}
		map[2][1] = tmp3;
		map[u][1] = -1;
	}
	
	static void lower_wind(int l) {
		int tmp = map[l][C];
		for(int i=C; i>2; i--) {
			map[l][i] = map[l][i-1];
		}
		map[l][2] = 0;
		int tmp2 = map[R][C];
		for(int i=R; i>l+1; i--) {
			map[i][C] = map[i-1][C];
		}
		map[l+1][C] = tmp;
		int tmp3 = map[R][1];
		for(int i=1; i<C-1; i++) {
			map[R][i] = map[R][i+1];
		}
		map[R][C-1] = tmp2;
		for(int i=l; i<R-1; i++) {
			map[i][1] = map[i+1][1];
		}
		map[R-1][1] = tmp3;
		map[l][1] = -1;
	}
	
	static void spread() {
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j] >= 5) {
					int amount = map[i][j] / 5;
					int cnt = 0;
					for(int k=0; k<4; k++) {
						int newi = i + dx[k];
						int newj = j + dy[k];
						if(newi < 1 || newi > R || newj < 1 || newj > C || map[newi][newj] == -1) continue;
						moved_map[newi][newj] += amount;
						cnt++;
					}
					map[i][j] = map[i][j] - (amount * cnt);
				}
			}
		}
	}
	
}
