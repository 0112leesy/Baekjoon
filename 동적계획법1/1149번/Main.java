import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	
	static int cost[][], ans[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		cost = new int[N][3]; // 입력 받게 될 색깔 별 비용 저장
		ans = new int[N][3]; // 각 단계 별 최적 비용 누적 합 저장
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		// 누적 합 초기화
		ans[0][0] = cost[0][0];
		ans[0][1] = cost[0][1];
		ans[0][2] = cost[0][2];
		
		// 맨 마지막 단계에서의 누적 합 중 최솟 값 출력
		int ans = Math.min(func(N-1, 0), func(N-1, 1));
		ans = Math.min(ans, func(N-1, 2));
		
		System.out.println(ans);
	}
	
	// 재귀적으로 풀이 (그러나 반복문을 통해 조금 더 직곽적인 풀이가 가능함)
	static int func(int n, int idx) {
		// n 단계에서 idx 색으로 집을 칠할 때, 최적 누적 비용은 n-1 단계에서 idx 외의 색으로 집을 칠할 때의 최소 비용을 해당 비용에 더한 것임
		if(ans[n][idx] == 0) {
			
			if(idx == 0) {
				ans[n][idx] = Math.min(func(n-1,1), func(n-1,2)) + cost[n][idx];
			}
			else if(idx == 1) {
				ans[n][idx] += Math.min(func(n-1,0), func(n-1,2)) + cost[n][idx];
			}
			else if(idx == 2) {
				ans[n][idx] += Math.min(func(n-1,0), func(n-1,1)) + cost[n][idx];
			}
		}
		
		return ans[n][idx];
				
	}


	
}