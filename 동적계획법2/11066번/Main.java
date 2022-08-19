import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] dp; // dp[i][j]: i장부터 j장까지 합칠때의 최소비용
	static int[] arr, cum;
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			K = Integer.parseInt(br.readLine());
			arr = new int[K];
			cum = new int[K];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int total = 0;
			for(int i=0; i<K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				total += arr[i];
				cum[i] = total; // i번째 숫자까지의 누적합 저장
			}

			dp = new int[K][K];
			
			for(int i=0; i<K; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
			for(int i=0; i<K-1; i++) { // dp배열 초기화
				dp[i][i] = arr[i];
				dp[i][i+1] = arr[i] + arr[i+1];
			}
			dp[K-1][K-1] = arr[K-1];
			
			sb.append(sum_file(0, K-1)).append('\n');
		}
		System.out.println(sb);
	}
	
	static int sum_file(int from, int to) {
		if(dp[from][to] != Integer.MAX_VALUE) return dp[from][to];
		for(int i=from; i<to; i++) { // from ~ i까지의 파일을 합친 것과 i ~ to까지의 파일을 합친 것을 더한다고 가정
             // 각각의 파일 크기를 더한 뒤 from ~ to까지의 파일크기의 누적합을 더하여 비용 계산
			int tmp = sum_file(from,i) + sum_file(i+1,to) + cum[to] - cum[from] + arr[from];
			if(i == from) { // 맨 처음파일과 맨 끝파일만 남았을 때는 파일 크기를 한번 빼줌
				tmp -= arr[from];
			}
			else if(i == to-1) {
				tmp -= arr[to];
			}
			dp[from][to] = Math.min(dp[from][to], tmp);
		}
		return dp[from][to];
	}

}
