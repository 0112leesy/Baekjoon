import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int arr[][][];
	static int dx[] = {-1, 1, 0, 0, 0, 0}, dy[] = {0, 0, -1, 1, 0, 0}, dz[] = {0, 0, 0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		arr = new int[H+1][N+1][M+1];
		Queue<Integer[]> queue = new LinkedList<>();
		
		for(int i=1; i<=H; i++) {
			for(int j=1; j<=N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=1; k<=M; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
					if(arr[i][j][k] == 1) {
						queue.offer(new Integer[] {i, j, k});
					}
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Integer[] temp = queue.poll();
			int z = temp[0];
			int x = temp[1];
			int y = temp[2];
			
			for(int i=0; i<6; i++) {
				int newz = z + dz[i];
				int newx = x + dx[i];
				int newy = y + dy[i];
				
				if(newz <= 0 || newz > H || newx <= 0 || newx > N || newy <= 0 || newy > M) continue;
				if(arr[newz][newx][newy] == 0) {
					arr[newz][newx][newy] = arr[z][x][y] + 1;
					queue.offer(new Integer[] {newz, newx, newy});

				}
			}
		}
	
			int Max = 0;
			boolean pos = true;
			
			for(int i=1; i<=H; i++) {
				if(!pos) break;
				for(int j=1; j<=N; j++) {
					for(int k=1; k<=M; k++) {
						if(arr[i][j][k] == 0) {
							pos = false;
							break;
						}
						Max = Math.max(Max, arr[i][j][k]);
					}
				}
			}
			
			System.out.println(pos ? Max-1 : -1);
            // 토마토가 이미 모두 익어있는 경우 Max는 1
		
	}


}
