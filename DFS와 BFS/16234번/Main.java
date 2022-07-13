import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, L, R;
	static int[][] pop;
	static int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};
	static boolean[][] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		pop = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) pop[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int total_move = 0;
		boolean flag = true;
		
		while(flag) {
//			System.out.println(total_move+" :");
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(pop[i][j]+" ");
//				}
//				System.out.println();
//			}
			visited = new boolean[N][N];
			
			int union_cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j= 0; j<N; j++) {
					if(!visited[i][j]) { // 새로운 탐색 시작				
						Queue<Integer[]> queue = new LinkedList<>();
						queue.offer(new Integer[] {i, j});
						visited[i][j] = true;
						
						ArrayList<Integer[]> union_list = new ArrayList<>();
						int united_pop = pop[i][j];
						union_list.add(new Integer[] {i, j});
						
						while(!queue.isEmpty()) {
							Integer[] tmp = queue.poll();
							int now_x = tmp[0];
							int now_y = tmp[1];
							
							for(int k=0; k<4; k++) {
								int next_x = now_x + dx[k];
								int next_y = now_y + dy[k];
								if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= N) continue;
								int sub = Math.abs(pop[now_x][now_y] - pop[next_x][next_y]);
								if(!visited[next_x][next_y] && L<=sub && sub<=R) {
									queue.offer(new Integer[] {next_x, next_y});
									visited[next_x][next_y] = true;
									union_list.add(new Integer[] {next_x, next_y});
									united_pop += pop[next_x][next_y];
									union_cnt++;
								}
							}
						}
						int union_pop = united_pop / union_list.size();
						for(Integer[] current : union_list) {
							pop[current[0]][current[1]] = union_pop;
						}
					}
					
				}
			}
			if(union_cnt == 0) break;
			total_move++;
		}
		System.out.println(total_move);
	}
}
 {
    
}
