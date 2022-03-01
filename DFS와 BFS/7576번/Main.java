import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int arr[][];
	static int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M+1];
		Queue<Integer[]> queue = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				int tomato = Integer.parseInt(st.nextToken());
				arr[i][j] = tomato;
				
				if(tomato == 1) {
					queue.offer(new Integer[] {i, j});
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Integer[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int i=0; i<4; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];
				
				if(newx <= 0 || newx > N || newy <=0 || newy > M) continue;
				if(arr[newx][newy] == 0) {
					arr[newx][newy] = arr[x][y] + 1;
					queue.offer(new Integer[] {newx, newy});

				}
			}
		}
		
		int distMax = 0;
		boolean pos = true;
		for(int i=1; i<=N; i++) {
			if(!pos) break;
			for(int j=1; j<=M; j++) {
				if(arr[i][j] == 0) {
					pos = false;
					break;
				}
				distMax = Math.max(distMax, arr[i][j]);
			}
		}
		
		System.out.println(pos? distMax-1 : -1);
		
	}


}