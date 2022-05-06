import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		System.out.println(dp(N));
		
	}
	
	static int dp(int t) {
		// 왼쪽 위 타일을 1*2로 채운 경우: 남은 타일은 (n-1)*2
		// 왼쪽 위 타일을 2*1로 채운 경우: 남은 타일은 (n-2)*2
		if(dp[t] != 0) return dp[t];
		if(t == 1) return 1;
		if(t == 2) return 2;
		else return dp[t] = (dp(t-1) + dp(t-2)) % 10007;
	}

}
