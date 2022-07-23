import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] cost, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		cost = new int[N][3];
		ans = new int[N][3];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 1번집을 R로 칠했을 때 - N번집은 G 또는 B여야함
		ans[0][0] = cost[0][0];
		ans[0][1] = 10000;
		ans[0][2] = 10000;
		int R_min = Math.min(minCost(N-1, 1), minCost(N-1, 2));
		
		// 1번집을 G로 칠했을 때 - N번집은 R 또는 B여야함
		ans = new int[N][3];
		ans[0][0] = 10000;
		ans[0][1] = cost[0][1];
		ans[0][2] = 10000;
		int G_min = Math.min(minCost(N-1, 0), minCost(N-1, 2));
		
		// 1번집을 B로 칠했을 때 - N번집은 R 또는 G여야함
		ans = new int[N][3];
		ans[0][0] = 10000;
		ans[0][1] = 10000;
		ans[0][2] = cost[0][2];
		int B_min = Math.min(minCost(N-1, 0), minCost(N-1, 1));
		
		int min = Math.min(R_min, G_min);
		min = Math.min(min, B_min);
		
		System.out.println(min);
		
	}
	// top-down 방식
	static int minCost(int n, int idx) {
		if(ans[n][idx] == 0) {
			if(idx == 0) {
				ans[n][idx] = Math.min(minCost(n-1, 1), minCost(n-1, 2)) + cost[n][idx];
			}
			else if(idx == 1) {
				ans[n][idx] = Math.min(minCost(n-1, 0), minCost(n-1, 2)) + cost[n][idx];
			}
			else if(idx == 2) {
				ans[n][idx] = Math.min(minCost(n-1, 0), minCost(n-1, 1)) + cost[n][idx];
			}
		}
		return ans[n][idx];
	}
	
}
