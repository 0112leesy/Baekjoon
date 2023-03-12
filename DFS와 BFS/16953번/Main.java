import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 코드 수정 필요
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int cnt = 1;
		while(M != N) {
			System.out.println(M);
			if(M % 2 != 0 && M % 10 != 1) {
				break;
			}
			
			if(M % 2 == 0) {
				M /= 2;
				cnt++;
			}
			else if(M % 10 == 1) {
				M /= 10;
				cnt++;
			}
			else if (M < N) {
				break;
			}
		}
		
		if(cnt == 1) cnt = -1;
		System.out.println(cnt);
	}
}