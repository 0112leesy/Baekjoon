import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 백트래킹: 시간초과
    // 그리디: 틀렸습니다
    // bottom-up DP로 풀이

	static int n;
	static int[][] score;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T -- > 0) {
			n = Integer.parseInt(br.readLine());
			score = new int[n][2];
			for(int i=0; i<2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					score[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[n+1][2];
			dp[1][0] = score[0][0];
			dp[1][1] = score[0][1];
			
			for(int i=2; i<=n; i++) {
				dp[i][0] = Math.max(dp[i-2][1], dp[i-1][1]) + score[i-1][0];
				dp[i][1] = Math.max(dp[i-2][0], dp[i-1][0]) + score[i-1][1];
			}
			
			sb.append(Math.max(dp[n][0], dp[n][1])).append('\n');
		}
		System.out.println(sb.toString());
	}
}