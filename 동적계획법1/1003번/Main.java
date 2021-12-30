import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	
	static int N, zero, one, zero_plus_one; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());
			zero = 1;
			one = 0;
			zero_plus_one = 1;
			for(int j=0; j<N; j++) { // 규칙성 찾기 필요
				zero = one;
				one = zero_plus_one;
				zero_plus_one = zero + one;
			}
			sb.append(zero+" "+one).append('\n');
		}
		System.out.println(sb);
 
	}
 
}