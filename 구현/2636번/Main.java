import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;
	static int[][] cheese;
	static int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cheese = new int[R+1][C+1];
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=C; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		getAnswer();
		
	}
	
	static void getAnswer() {
		List<Integer[]> surface = getSurface();
		
		int time = 0;
		int count = 0;
		while(!surface.isEmpty()) {
			count = surface.size();
			// 치즈 녹이기
			time += 1;
			for(Integer[] tmp : surface) {
				cheese[tmp[0]][tmp[1]] = 0;
			}
			
			surface = getSurface();
		}
		
		System.out.println(time + "\n" + count);
	}
	
	
	static List<Integer[]> getSurface() {
		
		Queue<Integer[]> queue=  new LinkedList<>();
		boolean[][] visited = new boolean[R+1][C+1];
		
		queue.offer(new Integer[] {0, 0});
		visited[0][0] = true;
		
		List<Integer[]> list = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			Integer[] tmp = queue.poll();
			int nowx = tmp[0];
			int nowy = tmp[1];
			
			for(int i=0; i<4; i++) {
				int nextx = nowx + dx[i];
				int nexty = nowy + dy[i];
				
				if(nextx < 0 || nextx > R || nexty < 0 || nexty > C || visited[nextx][nexty]) continue;
				if(cheese[nextx][nexty] == 1) {
					visited[nextx][nexty] = true;
					list.add(new Integer[] {nextx, nexty});
				}
				else {
					visited[nextx][nexty] = true;
					queue.offer(new Integer[] {nextx, nexty});
				}
			}
		}

		return list;
	}
}