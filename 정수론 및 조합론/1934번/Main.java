import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if(A < B) {
				int temp = A;
				A = B;
				B = temp;
			}
			sb.append(A * B / getGCD(A, B)).append("\n");
		}
		System.out.println(sb);
	}
	
	static int getGCD(int a, int b) {
		if(b != 0) {
			return getGCD(b, a%b);
		}
		return a;
	}

}