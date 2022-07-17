import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, B;
	static int[][] map;
	static int[][] select = new int[3][2];
	static int res = 0;
	static ArrayList<Integer[]> virus = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virus.add(new Integer[] {i, j});
				}
				else if(map[i][j] == 1) B++;
			}
		}
		
		combi_wall(0, 0, 0);
		
		System.out.println(res);
	}
	
	static void combi_wall(int cnt, int x, int y) {
		
		if(cnt == 3) {
			spread_virus();
			return;
		}
		
		for(int j=y; j<M; j++) {
			if(map[x][j] == 0) {
				select[cnt][0] = x;
				select[cnt][1] = j;
				combi_wall(cnt+1, x, j+1);
			}
		}
		
		if(x+1<N) {
			for(int i=x+1; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0) {
						select[cnt][0] = i;
						select[cnt][1] = j;
						combi_wall(cnt+1, i, j+1);
					}
				}
			}
		}
	}
	
	static void spread_virus() {
		for(int i=0; i<3; i++) {
			map[select[i][0]][select[i][1]] = -1;
		}
		Queue<Integer[]> queue = new LinkedList<>();
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int cnt = 0;
		
		for(Integer[] v : virus) {
			queue.add(v);
			while(!queue.isEmpty()) {
				int x = queue.peek()[0];
				int y = queue.peek()[1];
				queue.poll();
				for(int i=0; i<4; i++) {
					int newx = x+dx[i];
					int newy = y+dy[i];
					if(newx<0 || newx>=N || newy<0 || newy>=M) continue;
					if(map[newx][newy]!=0) continue;
					map[newx][newy] = -2;
					cnt++;
					queue.add(new Integer[] {newx, newy});
				}
			}
		}
		res = Math.max(res, N*M - B - 3 - virus.size() - cnt);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==-2 || map[i][j]==-1) map[i][j] = 0;
			}
		}
	}
}
