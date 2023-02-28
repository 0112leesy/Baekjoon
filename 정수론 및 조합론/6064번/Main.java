import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			sb.append(getAnswer(M, N, x, y)).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	static int getAnswer(int M, int N, int x, int y) {
		if(M < N) {
			int tmp = M;
			M = N;
			N = tmp;
			
			int tmp2 = x;
			x = y;
			y = tmp2;
		}
		
		// System.out.println(M + " and " + N);
		
		int gcd = getGCD(M, N);
		// System.out.println("gcd : " + gcd);
		int max = M * N / gcd;
		int rangeM = max / M; // = N / gcd
		int rangeN = max / N; // = M / gcd
		
		// System.out.println(rangeM + " " + rangeN);

//		for(int i=0; i<rangeM; i++) { // 시간초과
//			for(int j=0; j<rangeN; j++) {
//				if((i * M + x) == (j * N + y)) {
//					return i * M + x;
//				}
//			}
//		}
//		return -1;
		
		int indexI = 0;
		int indexJ = 0;
		while(indexI < rangeM && indexJ < rangeN) {
			if((indexI * M + x) == (indexJ * N + y)) {
				return indexI * M + x;
			}
			else if((indexI * M + x) > (indexJ * N + y)) {
				indexJ++;
			}
			else {
				indexI++;
			}
		}
		return -1;
		
	}
	
	static int getGCD(int num1, int num2) { // 항상 num1이 num2보다 크다
		int a = num1;
		int b = num2;
		int r = a%b;
		while(r != 0) {
			a = b;
			b = r;
			r = a%b;
		}
		return b;
	}
    // (+) 중국인의 나머지 정리 참조
}