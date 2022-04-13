import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;	// 행렬의 수; M1, M2, ..., MN
	static int[] rows; // 행 정보; Mn은 rows[n-1] * rows[n] 행렬
	static int[][] mmm; // MinMatMult; mmm[from][to]는 Mfrom 부터 Mto까지 곱하는데의 최소 곱셈 횟수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		rows = new int[N+1];
		mmm = new int[N+1][N+1];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			rows[i] = Integer.parseInt(st.nextToken());
			if(i == N-1) rows[i+1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) mmm[i][i] = 0; // mmm[from][to]에서 from가 to가 같다면 연산 횟수는 0
		
		for(int len=1; len <= N-1; len++) { // len: 곱할 행렬들의 수
			for(int from=1; from<=N-len; from++) { // from 범위
				int to = from + len;

				mmm[from][to] = Integer.MAX_VALUE;
				
				// mmm[from][to]는
				// Mfrom, ... Mk ... , Mto까지의 행렬들을 곱할 때의 최소 곱셈 횟수임
				// 이는 mmm[from][k] * mmm[k+1][to] + rows[from-1] * rows[k] * rows[to]의 최솟값으로 결정됨
				// k는 from, from+1 , ... , to-1까지의 숫자를 가질 수 있음
				for(int k=from; k<to; k++) {
					mmm[from][to] = Math.min(mmm[from][to], 
							mmm[from][k] + mmm[k+1][to] + rows[from-1] * rows[k] * rows[to]);
				}
			}
		}
		
		// M1 부터 MN 까지 곱할 때의 최소 곱셈 횟수를 출력
		System.out.println(mmm[1][N]);
	}
	
	
}


