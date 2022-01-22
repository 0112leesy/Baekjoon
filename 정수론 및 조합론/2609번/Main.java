import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		if(B > A) {
			int temp = A;
			A = B;
			B = temp;
		}
		int GCD = getGCD(A, B);
		int LCM = A * B / GCD;
		System.out.println(GCD+"\n"+LCM);
		
	}
	
	static int getGCD(int a, int b) {
		int remain = 0;
		while(b!=0) {
			remain = a%b;
			a = b;
			b = remain;
		}
		return a;
	}

}