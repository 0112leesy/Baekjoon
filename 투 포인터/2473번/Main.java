import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
        // 최대 1,000,000,000을 5000번 입력받을 수 있으므로 long 타입으로 선언해야함
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Long.parseLong(st.nextToken());
		Arrays.sort(arr);

		long min = Long.MAX_VALUE;
		long liq1 = 0;
		long liq2 = 0;
		long liq3 = 0;
		
		for(int i=0; i<=arr.length-3; i++) {
			int fix = i;
			int l = fix+1;
			int r = arr.length-1;
			
			while(l < r) {
				long sum = arr[fix] + arr[l] + arr[r];
				long abs = Math.abs(sum);
				
				if(abs < min) {
					min = abs;
					liq1 = arr[fix];
					liq2 = arr[l];
					liq3 = arr[r];
				}
				
				if(arr[fix] + arr[l] + arr[r] >= 0) { // 값이 작아져야 함
					r--;
				}
				else if(arr[fix] + arr[l] + arr[r] < 0) { // 값이 커져야 함
					l++;
				}
			}
		}
		System.out.println(liq1+" "+liq2+" "+liq3);
	}
}
