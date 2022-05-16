import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[] ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		ans = new int[11];
		ans[1] = 1;
		ans[2] = 2;
		ans[3] = 4;
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp(n)).append('\n');
		}
		System.out.println(sb);
		
	}
	
	static int dp(int x) {
		if(ans[x] != 0) return ans[x];
		return ans[x] = dp(x-1) + dp(x-2) + dp(x-3);
 	}
}
