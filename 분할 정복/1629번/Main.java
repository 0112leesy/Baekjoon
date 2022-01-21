import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	
	static int A,B,C;
 
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.println(func(A, B));
	}

	static long func(long a, long b) {
		if(b > 1) { // b>1일때 a^b = a^(b/2) * a^(b/2) (* a : 홀수일때)
			long temp = func(a, b/2);
			if(b%2==1) return ((temp*temp%C) * (a%C)) %C; // 홀수일때
			return temp*temp%C; // 짝수일때
			// 모듈러 연산의 분배법칙
			// (A*B)%C = ((A%C) * (B%C)) % C
		}
		return a%C; // b==1일때 리턴
	}
	

}