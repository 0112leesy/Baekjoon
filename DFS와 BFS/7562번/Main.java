import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
	static int I, start_x, start_y, end_x, end_y;
	static int[][] dist;
	static Queue<Integer[]> queue;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T =  Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			I = Integer.parseInt(br.readLine());
			dist = new int[I][I];

			StringTokenizer st = new StringTokenizer(br.readLine());
			start_x = Integer.parseInt(st.nextToken());
			start_y = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			end_x = Integer.parseInt(st.nextToken());
			end_y = Integer.parseInt(st.nextToken());
			
			queue = new LinkedList<Integer[]>();
			queue.offer(new Integer[] {start_x, start_y});
			dist[start_x][start_y] = 1;
			
			int ans = 0;
			
			while(!queue.isEmpty()) {
				Integer[] temp = queue.poll();
				int x = temp[0];
				int y = temp[1];
				
				for(int i=0; i<8; i++) {
					int newx = x + dx[i];
					int newy = y + dy[i];
					
					if(newx < 0 || newx >= I || newy < 0 || newy >= I) continue;
					if(dist[newx][newy] == 0) {
						dist[newx][newy] = dist[x][y] + 1;
						queue.offer(new Integer[] {newx, newy});
					}
				}
			}
			
			sb.append(dist[end_x][end_y] - 1).append('\n');
		}
		System.out.println(sb);
	}

}
