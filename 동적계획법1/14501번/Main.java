import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N];
		int[] P = new int[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		// dp: N일에 얻을 수 있는 최대 수익
		int[] dp = new int[N+1];
		
		// 현재 날짜에서 소요 시간을 더했을 때 N보다 작은지 판단
		// 작을 경우, 종료 날짜에서의 dp값이 더 크다면 유지, 아니라면 비용을 더하여 갱신
		// 다음 날짜의 dp값은, 다음 날짜의 dp값 또는 현재 날짜의 dp값 중 큰 값으로 결정
		for(int i=0; i<N; i++) {
			if(i + T[i] <= N) {
				dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
			}
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
		// N일에서의 최대 수익을 출력
		System.out.println(dp[N]);
	}
	
}
