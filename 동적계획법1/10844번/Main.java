import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
	
	static Long dp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new Long[N+1][10];
		for(int i=0; i<=9; i++) {
			dp[1][i] = 1L;
		}
		long ans = 0;
		for(int i=1; i<=9; i++) {
			ans += func(N, i);
		}
		System.out.println(ans % 1000000000);
		// dp 배열을 Integer로 선언하였고 
		// ans에 나누기 연산을 해주지 않아서 2번 틀림
	}
	
	static long func(int len, int idx) {
		// len은 계단수의 길이, idx는 계단수의 첫번째 수를 의미
		// len 길이의, idx 수로 시작하는 계단수의 개수는
		// len-1 길이의, idx-1 수와 idx+1 수로 시작하는 계단수의 개수의 합과 같다
		
		if(dp[len][idx] == null) {
			if(idx == 0) {
				dp[len][idx] = func(len-1, idx+1)% 1000000000;
			}
			else if(idx == 9) {
				dp[len][idx] = func(len-1, idx-1)% 1000000000;
			}
			else {
				dp[len][idx] = (func(len-1, idx-1) + func(len-1, idx+1))% 1000000000;
			}
		}
		return dp[len][idx];
	}

	
}