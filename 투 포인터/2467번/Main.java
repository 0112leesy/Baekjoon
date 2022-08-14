import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Long.parseLong(st.nextToken());
		
		int l = 0;
		int r = arr.length-1;
		long abs = Long.MAX_VALUE;
		int[] ans = new int[2];
		
		while(l < r) {
			long tmp = Math.abs(arr[l] + arr[r]);
			if(tmp < abs) {
				ans[0] = l;
				ans[1] = r;
				abs = tmp;
			}
			
			if(arr[l] + arr[r] >= 0) {
				r--;
			}
			else {
				l++;
			}
		}
		
		System.out.println(arr[ans[0]]+" "+arr[ans[1]]);
	}

}
