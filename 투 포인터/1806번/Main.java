import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main { // l=0, r=0으로 초기화하여 탐색, sum += sum[r++]로 수정 등 코드 개선 가능

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long S = Long.parseLong(st.nextToken());
		
		ArrayList<Long> arr = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr.add(Long.parseLong(st.nextToken()));
		
		if(arr.get(0) >= S) {
			System.out.println(1);
			return;
		}
		
		int l = 0;
		int r = l+1;
		int min = Integer.MAX_VALUE;
		
		long sum = arr.get(l) + arr.get(r);
		
		while(l < r) {
			
			if(sum < S) { // 값이 커져야 함
				r++;
				if(r == N) break;
				sum += arr.get(r);
			}
			else { // S 이상이면 min 값 일단 갱신하고, 그 다음 값 줄여봄
				 min = Math.min(min, r-l+1);
				 l++;
				 if(l == r && sum-arr.get(l-1) >= S) {
					 min = 1;
					 break;
				 }
				 sum -= arr.get(l-1);
			}

		}
		if(min == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(min);
	}
}
