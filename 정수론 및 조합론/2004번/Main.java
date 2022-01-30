import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
 
public class Main {	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// nCm = n! / m!*(n-m)!
		// 2와 5의 제곱으로 표현하면 2^(a-b-c) * 5^(a'-b'-c')처럼 됨
		// 이 중 더 작은 승 수로 0의 개수가 결정됨
		int ans = Math.min(pow_two(n) - pow_two(m) - pow_two(n-m),
				pow_five(n) - pow_five(m) - pow_five(n-m));
		System.out.println(ans);
		
	}
	
	static int pow_two(int num) {
		int ans = 0;
		while(num >= 2) {
			ans += num/2;
			num /= 2;
		}
		return ans;
	}
	
	static int pow_five(int num) {
		int ans = 0;
		while(num >= 5) {
			ans += num/5;
			num /= 5;
		}
		return ans;
	}
	
	
 
}