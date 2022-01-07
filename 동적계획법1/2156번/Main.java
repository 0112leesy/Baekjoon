import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
	
	static int N;
	static int arr[];
	static Integer dp[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		dp = new Integer[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		dp[0] = 0;
		dp[1] = arr[1];
		if(N >= 2) dp[2] = arr[1] + arr[2];
		
		System.out.println(func(N));
		
	}
	
	static int func(int x) {
		// 조건을 만족하며 현재 포도주까지 마신 양과, 현재 포도주를 마시지 않고 이전에 마셨던 양 중 최댓값을 dp에 저장한다
		if(dp[x] == null) {
			dp[x] = Math.max(Math.max(func(x-2), func(x-3) + arr[x-1]) + arr[x], func(x-1)); 
		}
		
		return dp[x];
	}


	
}