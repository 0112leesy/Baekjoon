import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] number;
	static int[] dx = {1, 1, 1};
	static int[] dy = {-1, 0, 1};
	static int[][] maxScore;
	static int[][] minScore;
	static final int INF = 1000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		number = new int[N][3];
		for(int i=0; i<N; i++ ) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				number[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = 0;
		for(int i=0; i<3; i++) {
			max = Math.max(max, maxDp(0, i));
		}
		
		int min = INF;
		for(int i=0; i<3; i++) {
			min = Math.min(min, minDp(0, i));
		}
		
		System.out.println(max+" "+min);
		
	}
	
	static int maxDp(int startx, int starty) {
		maxScore = new int[number.length][number[0].length];
		maxScore[startx][starty] = number[startx][starty];
		
		Queue<Integer[]> queue = new LinkedList<>();
		queue.offer(new Integer[] {startx, starty});
		
		while(!queue.isEmpty()) {
			Integer[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int i=0; i<3; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];
				
				if(newx >= N || newy < 0 || newy > 2) continue;
				if(maxScore[newx][newy] < maxScore[x][y] + number[newx][newy]) {
					maxScore[newx][newy] = maxScore[x][y] + number[newx][newy];
					queue.offer(new Integer[] {newx, newy});
				}
			}
		}
		
		int max = 0;
		for(int i=0; i<3; i++) {
			max = Math.max(max, maxScore[N-1][i]);
		}
		return max;
	}
	
	static int minDp(int startx, int starty) {
		minScore = new int[number.length][number[0].length];
		for(int i=0; i<minScore.length; i++) {
			Arrays.fill(minScore[i], INF);
		}
		
		minScore[startx][starty] = number[startx][starty];
		
		Queue<Integer[]> queue = new LinkedList<>();
		queue.offer(new Integer[] {startx, starty});
		
		while(!queue.isEmpty()) {
			Integer[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int i=0; i<3; i++) {
				int newx = x + dx[i];
				int newy = y + dy[i];
				
				if(newx >= N || newy < 0 || newy > 2) continue;
				if(minScore[newx][newy] > minScore[x][y] + number[newx][newy]) {
					minScore[newx][newy] = minScore[x][y] + number[newx][newy];
					queue.offer(new Integer[] {newx, newy});
				}
			}
		}
		
		int min = INF;
		for(int i=0; i<3; i++) {
			min = Math.min(min, minScore[N-1][i]);
		}
		return min;
	}
	
}