import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/* 주사위 배열) 1 2 3 4 5 6
	 *   2
	 * 4 1 3
	 *   5
	 *   6
	 * 동쪽으로 이동 시) 4 2 1 6 5 3
	 * 서족으로 이동 시) 3 2 6 1 5 4
	 * 남쪽으로 이동 시) 2 6 3 4 1 5
	 * 북쪽으로 이동 시) 5 1 3 4 6 2
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		int[] dice = new int[7];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(K-- > 0) {
			int com = Integer.parseInt(st.nextToken());
			int d1 = dice[1];
			int d2 = dice[2];
			int d3 = dice[3];
			int d4 = dice[4];
			int d5 = dice[5];
			int d6 = dice[6];
			switch(com) {
			case 1: // 동쪽
				if(y+1 >= M) continue;
				y+=1;
				dice[1] = d4;
				dice[2] = d2;
				dice[3] = d1;
				dice[4] = d6;
				dice[5] = d5;
				dice[6] = d3;
				if(map[x][y] == 0) {
					map[x][y] = dice[6];
				}
				else {
					dice[6] = map[x][y];
					map[x][y] = 0;
				}
				sb.append(dice[1]).append('\n');
				break;
			case 2: // 서쪽
				if(y-1 < 0) continue;
				y-=1;
				dice[1] = d3;
				dice[2] = d2;
				dice[3] = d6;
				dice[4] = d1;
				dice[5] = d5;
				dice[6] = d4;
				if(map[x][y] == 0) {
					map[x][y] = dice[6];
				}
				else {
					dice[6] = map[x][y];
					map[x][y] = 0;
				}
				sb.append(dice[1]).append('\n');
				break;
			case 3: // 북쪽
				if(x-1 < 0) continue;
				x-=1;
				dice[1] = d5;
				dice[2] = d1;
				dice[3] = d3;
				dice[4] = d4;
				dice[5] = d6;
				dice[6] = d2;
				if(map[x][y] == 0) {
					map[x][y] = dice[6];
				}
				else {
					dice[6] = map[x][y];
					map[x][y] = 0;
				}
				sb.append(dice[1]).append('\n');
				break;
			case 4: // 남쪽
				if(x+1 >= N) continue;
				x+=1;
				dice[1] = d2;
				dice[2] = d6;
				dice[3] = d3;
				dice[4] = d4;
				dice[5] = d1;
				dice[6] = d5;
				if(map[x][y] == 0) {
					map[x][y] = dice[6];
				}
				else {
					dice[6] = map[x][y];
					map[x][y] = 0;
				}
				sb.append(dice[1]).append('\n');
				break;
			}
		}
		System.out.println(sb);
	}
	
}
