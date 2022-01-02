import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
	
	static long arr[] = new long[101];
 	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
        // 변수 초기화 시 (long) 1 또는 1L처럼 캐스팅 필요
		arr[1] = 1L;
		arr[2] = 1L;
		arr[3] = 1L;
		arr[4] = 2L;
		arr[5] = 2L;
		
		for(int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(func(N)).append('\n');
		}
		System.out.println(sb);
	}
	
	static long func(int n) {
		if(arr[n] == 0) {
			arr[n] = func(n-1) + func(n-5);
		}
		return arr[n];
	}
    // n>=6일때, arr[n] = arr[n-1] + arr[n-5] 로 점화식을 구하였으나
    // n>=4일때, arr[n] = arr[n-2] + arr[n-3] 도 성립함

	
}