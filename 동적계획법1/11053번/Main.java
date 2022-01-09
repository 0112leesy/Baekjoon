import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	
	static int arr[];
	static Integer ans[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		arr = new int[A+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=A; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		ans = new Integer[A+1];
		
		int max = 0;
		for(int i=1; i<=A; i++) {
			// ans 배열 값중 최댓값을 찾아줌
			max = Math.max(max, func(i));
		}
		System.out.println(max);
	}
	
	static int func(int x) {
		// ans[x] : arr[x]까지의 수열 중 최장증가수열의 길이
		// LIS 알고리즘 사용: O(N^2)의 시간복잡도
		// ans[x]는 arr[x]까지의 수열 중, arr[x]보다 작은 수 arr[i]의 ans[i]+1과 ans[x] 중의 최댓값을 대입한 것
		if(ans[x] == null) {
			ans[x] = 1; // 탐색하지 않았을 때, 1로 초기화 후 비교 진행
			for(int i=1; i<x; i++) {
				if(arr[i] < arr[x]) {
					ans[x] = Math.max(func(i)+1, ans[x]);
				}
			}
		}
		
		return ans[x];
	}

	
}