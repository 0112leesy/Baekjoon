import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N]; // N*N 크기의 공간 선언
		Queue<Integer[]> queue = new LinkedList<>(); // 물고기 탐색을 위한 큐 선언

		int[][] visit = new int[N][N]; // 방문시간 저장
		
		for(int i=0; i<N; i++) { // 정보 입력받기	0:빈칸, 1~6:물고기의 크기, 9:상어위치
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int stat = Integer.parseInt(st.nextToken());
				map[i][j] = stat;
				
				if(stat == 9) { // 9가 입력되는 곳에서 탐색 시작되도록 큐에 삽입
					map[i][j] = 0; // map에는 0으로 설정
					queue.offer(new Integer[] {i,j});
					visit[i][j] = 1; // 방문 시간은 1
				}
			}
		}
		
		int total_time = 0; // 엄마 상어 부를때까지의 시간
		int shark_size = 2; // 상어의 크기
		int shark_ate = 0; // 상어가 먹은 물고기 (크기가 커지면 다시 0이 된다)
		
		while(true) { // 먹을 수 있는 물고기를 찾아보자
			
			int targetx = Integer.MAX_VALUE; // 먹을 물고기 x좌표
			int targety = Integer.MAX_VALUE; // 먹을 물고기 y좌표
			int dist = Integer.MAX_VALUE; // 먹을 물고기까지의 거리

			while(!queue.isEmpty()) {// 물고기를 먹기 위한 BFS
				Integer[] now = queue.poll();
				int x = now[0]; // 현재 x좌표
				int y = now[1]; // 현재 y좌표

				for(int i=0; i<4; i++) { // 해당 물고기 못먹으면 탐색이나 계속 하자
					int newx = x + dx[i];
					int newy = y + dy[i];
					
					// OOB, 재방문, 조건위배는 pass
					if(newx < 0 || newx >= N || newy < 0 || newy >= N || visit[newx][newy] != 0 || shark_size < map[newx][newy]) continue; 
					
					if(map[newx][newy] != 0 && shark_size > map[newx][newy]) {// 물고기를 먹을 수 있다
						if(dist > visit[x][y]) { // 거리가 dist보다 작으면 좌표와 거리를 갱신한다
							targetx = newx;
							targety = newy;
							dist = visit[x][y];
						}
						else if(dist == visit[x][y]) { // 거리는 같은데
							if(targetx > newx) { // x좌표가 더 작으면 좌표만 갱신한다
								targetx = newx;
								targety = newy;
							}
							else if(targetx == newx) { // x좌표도 같은데
								if(targety > newy) { // y 좌표가 더 작으면 y좌표만 갱신한다
									targety = newy;
								}
							}
						}
					}
					
					visit[newx][newy] = visit[x][y] + 1; // 방문 체크해주고
					queue.offer(new Integer[] {newx, newy}); // 큐에 삽입
					
				}
			}
			// BFS 종료 - 더이상 갈 수 있는 곳이 없어 !!
			if(targetx == Integer.MAX_VALUE && targety == Integer.MAX_VALUE) break; // 탐색 다했는데 dist가 갱신되지 않았다면 더이상 먹을 물고기가 없는거다 그만 찾아라
			
			shark_ate ++; // 그게 아니면 타깃 물고기를 먹자
			if(shark_ate == shark_size) {// 물고기를 먹었을 때 상어 사이즈와 같은 수라면
				shark_ate = 0; // 먹은 수를 다시 0으로 초기화 하고
				shark_size ++; // 상어 크기를 키운다					
			}
			
			total_time += dist;// 해당 물고기 먹을 때 까지의 시간 더해주기
			map[targetx][targety] = 0; // 물고기가 있던 칸을 빈칸으로 변경
			
			//초기화 작업 후 현재 BFS break
			visit = new int[N][N];
			queue.clear();
			visit[targetx][targety] = 1;
			queue.offer(new Integer[] {targetx,targety});
		}
		System.out.println(total_time);
	}

}
