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
		
		int cnt = 1;
		
		while(B != A) {
			// System.out.println(B);
			
			if(B % 10 == 1 && B / 10 > 0) {
				B /= 10;
				cnt += 1;
				continue;
			}
			else if(B % 2 == 0 && B / 2 > 0) {
				B /= 2;
				cnt += 1;
				continue;
			}
			else {
				break;
			}
		}
		
		if(B != A) cnt = -1;
		System.out.println(cnt);
	}
}