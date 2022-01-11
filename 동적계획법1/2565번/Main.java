import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class Main {
	// 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수
	// => 서로 교차하지 않게 하며 설치할 수 있는 전깃줄의 최대 개수를 구하면 됨
	// 즉 A 전봇대 기준으로 전깃줄 정보를 정렬하고, 연결된 B 전봇대의 위치를 확인해 LIS를 구하면 됨
	// LIS가 최대가 되는 위치의 전깃줄을 포함해 LIS 만큼의 전깃줄을 교차 없이 설치할 수 있다는 의미

	static int[][] arr;
	static Integer[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr, new Comparator<int[]>() { // 2차원 배열 정렬을 위해 Comparator 객체 추가

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}

		});
		
		dp = new Integer[N];
		int max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(max, func(i));
		}
		System.out.println(N-max-1);

	}
	
	static int func(int x) {
		if(dp[x] == null) {
			dp[x] = 0;
			for(int i=x-1; i>=0; i--) {
				if(arr[i][1] < arr[x][1]) {
					dp[x] = Math.max(dp[x], func(i) + 1);
				}
			}
		}
		
		return dp[x];
	}
	

}