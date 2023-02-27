import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n+1];
		Arrays.fill(dp, 5);
		
		for(int i=1; i<=n; i++) {
			long squareNum = (long)i*i;
			if(squareNum <= n) {
				int index = (int)squareNum;
				dp[index] = 1;
			}
			
			for(int j=1; j*j<=i; j++) {
				int newNum = dp[j*j] + dp[i - j*j];
				dp[i] = Math.min(dp[i], newNum);
			}
		}
		
		System.out.println(dp[n]);
		// System.out.println(dp(n));
		
	}
	
//	static int dp(int x) { // 재귀로 풀이 시 StackOverflow 발생
//		if(dp[x] != 0) {
//			return dp[x];
//		}
//		
//		int min = 5;
//		for(int i=1; i*i<=x; i++) {
//			int squareNum = i*i;
//			if(dp(squareNum) + dp(x-squareNum) < min) {
//				min = dp(squareNum) + dp(x-squareNum);
//			}
//		}
//		return dp[x] = min;
//	}
	
}