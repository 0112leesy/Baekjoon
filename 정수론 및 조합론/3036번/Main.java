import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
 
public class Main {	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int A = Integer.parseInt(st.nextToken());
		for(int i=1; i<N; i++) {
			int B = Integer.parseInt(st.nextToken());
			int GCD = 0;
			if(A >= B) {
				GCD = getGCD(A, B);
			}
			else {
				GCD = getGCD(B, A);
			}
			sb.append(A/GCD+"/"+B/GCD+"\n");
		}
		System.out.println(sb);
		
		
	}
	
	static int getGCD(int a, int b) {
		while(b != 0) {
			int r = a%b;
			a = b;
			b = r;
		}
		return a;
	}
	
 
}