import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	
	static int N;
	static int arr[][];
	static int sum[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N]; // 입력 받을 배열
		sum = new int[N][N]; // 최적 값을 저장할 배열
		// sum 배열 초기화
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sum[i][j] = -1;
			}
		}
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<=i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// sum 배열의 맨 마지막 행에 arr 배열 값 넣어주기
		for(int i=0; i<N; i++) {
			sum[N-1][i] = arr[N-1][i];
		}
		
		System.out.println(func(0,0));

	}
	// 맨 마지막 행부터 탐색이 시작되며, 결국 맨 첫번째 행에 최적 값이 오도록 한다
	// 해당 행의 최적 값은 다음 행의 인접한 최대 값과 현재의 값을 더한 것이다
	static int func(int x, int idx) {
		if(x == N-1) return sum[x][idx]; // 맨 마지막 행에 다다랐을 때, 바로 값을 리턴한다
		
		if(sum[x][idx] == -1) {
			sum[x][idx] = arr[x][idx] + Math.max(func(x+1, idx), func(x+1, idx+1));
		}
		
		return sum[x][idx];
	}
	


	
}