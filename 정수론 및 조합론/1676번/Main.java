
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
 
public class Main {	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		// N을 5로 나누면서 누적합 해주기
		// 5가 곱해진 만큼에 따라 팩토리얼 값의 0의 개수가 결정됨
		while(N >= 5) {
			ans += N / 5;
			N /= 5;
		}
		System.out.println(ans);
	}
	
 
}