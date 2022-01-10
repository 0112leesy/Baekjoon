import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	
	static int arr_fore[], arr_back[];
	static Integer dp_fore[], dp_back[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		arr_fore = new int[A];
		arr_back = new int[A];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<A; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr_fore[i] = num;
			arr_back[A-i-1] = num;
		}
		
		dp_fore = new Integer[A];
		dp_back = new Integer[A];

		for(int i=0; i<A; i++) {
			func(i, dp_fore, arr_fore);
			func(i, dp_back, arr_back);
		}
		// 바이토닉 수열의 길이는 LIS + LDS - 1
		int ans = 0;
		for(int i=0; i<A; i++) {
			ans = Math.max(ans, dp_fore[i] + dp_back[A-i-1]);
		}
		System.out.println(ans-1);


	}
	
	static int func(int x, Integer[] dp, int[] arr) {
		if(dp[x] == null) {
			dp[x] = 1;
			for(int i=0; i<x; i++) {
				if(arr[i] < arr[x]) {
					dp[x] = Math.max(func(i, dp, arr) + 1, dp[x]);
				}
			}
		}
		
		return dp[x];
	}


	
}