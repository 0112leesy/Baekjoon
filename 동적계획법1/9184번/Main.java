import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {

	static int[][][] temp = new int[21][21][21];
	static int a,b,c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == -1 && b == -1 && c == -1) break;
			// String.format 함수를 사용하였을 때 보다 빠르다
			sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(w_func(a, b, c)).append("\n");	
		}	
		System.out.println(sb);
	}

	static int w_func(int a, int b, int c) {  // 메모이제이션 기법 활용

		if(a <= 0 || b <= 0 || c <= 0) {
			return 1;
		}
		if(a > 20 || b > 20 || c > 20) {
			return temp[20][20][20] = w_func(20, 20, 20);
		}
		
		if(temp[a][b][c] != 0) return temp[a][b][c]; // 값이 0이 아니라면 이미 계산이 완료되었으므로 바로 해당 값을 리턴
		
		if(a < b && b < c) {
			return temp[a][b][c] = w_func(a, b, c-1) + w_func(a, b-1, c-1) - w_func(a, b-1, c);
		}
		
		return temp[a][b][c] = w_func(a-1, b, c) + w_func(a-1, b-1, c) + w_func(a-1, b, c-1) - w_func(a-1, b-1, c-1);

	}
 
}