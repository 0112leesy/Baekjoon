import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
	
	static int N, K;
	static int[] weight, value;
	static Integer[][] dp;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		weight = new int[N];
		value = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		dp = new Integer[K+1][N];
		
		System.out.println(func(K,N-1));
		
	}
	
	static int func(int x, int i) { // x는 현재 가방의 무게, 1~i번 물건을 고려했을 때
		if(i < 0) return 0; // 물건의 범위 밖
		if(dp[x][i] == null) {
			if(weight[i] > x) {
				dp[x][i] = func(x, i-1);
			}
			else {
				dp[x][i] = Math.max(func(x-weight[i],i-1) + value[i], func(x, i-1));
			}	
		}
		
		return dp[x][i];
	}
}