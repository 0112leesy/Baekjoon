import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {// 다시 풀어보기 -> 초기화 + bfs 코드 함수화 하여
	
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		Queue<Integer[]> queue = new LinkedList<>();

		int[][] visit = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int stat = Integer.parseInt(st.nextToken());
				map[i][j] = stat;
				
				if(stat == 9) {
					map[i][j] = 0;
					queue.offer(new Integer[] {i,j});
					visit[i][j] = 1;
				}
			}
		}
		
		int total_time = 0;
		int shark_size = 2;
		int shark_ate = 0;
		
		// 먹을 수 있는 물고기 고려 -> 상어보다 작은 물고기의 수 필요
		while(true) {// 먹을 수 있는 물고기가 있을 때
			
			int targetx = 20;
			int targety = 20;
			int dist = 100;

			while(!queue.isEmpty()) {// 물고기를 먹기 위한 BFS
				Integer[] now = queue.poll();
				int x = now[0];
				int y = now[1];
				
				if(map[x][y] != 0 && shark_size > map[x][y]) {// 물고기를 먹을 수 있다
					if(dist > visit[x][y]-1) {
						targetx = x;
						targety = y;
						dist = visit[x][y] - 1;
					}
					else if(dist == visit[x][y]-1) {
						if(targetx > x) {
							targetx = x;
							targety = y;
						}
						else if(targetx == x) {
							if(targety > y) {
								targety = y;
							}
						}
					}
				}
				
				for(int i=0; i<4; i++) {
					int newx = x + dx[i];
					int newy = y + dy[i];
					
					if(newx < 0 || newx >= N || newy < 0 || newy >= N) continue;
					if(shark_size >= map[newx][newy] && visit[newx][newy] == 0) {
						visit[newx][newy] = visit[x][y] + 1;
						queue.offer(new Integer[] {newx, newy});
					}
				}
			}
			// BFS 종료
			if(dist==100) break;
			
			//System.out.print("eat :"+targetx+","+targety+" time:");
			shark_ate ++;
			if(shark_ate == shark_size) {// 상어가 클 때
				shark_ate = 0;
				shark_size ++;					
			}
			//System.out.println(visit[targetx][targety]-1);
			total_time += dist;// 해당 물고기 먹을 때 까지의 시간 더해주기
			map[targetx][targety] = 0; // 물고기가 있던 칸을 빈칸으로 변경
			
			//초기화 작업 후 현재 BFS break
			visit = new int[N][N];
			queue.clear();
			visit[targetx][targety] = 1;
			queue.offer(new Integer[] {targetx,targety});
		}
		System.out.println(total_time);
		// 초기화 조건 수정 필요
	}
}
