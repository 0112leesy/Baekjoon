import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
	
	static int[] arr;
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[K];
		for(int i=0; i<K; i++) arr[i] = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		
        // long으로 선언하는 것에 주의
		long l = 1;
		long r = arr[K-1];
		while(l < r) {
			long mid = (l + r + 1) / 2;
			if(check(mid)) l = mid;
			else r = mid - 1;
		}
		
		System.out.println(l);
		
	}
	
	static boolean check(long x) {
		long cnt = 0;
		
		for(int i=0; i<K; i++) {
			cnt += (arr[i] / x);
		}
		
		if(cnt >= N) return true;
		else return false;
	}
	
}