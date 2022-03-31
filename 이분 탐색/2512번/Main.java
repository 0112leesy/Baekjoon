import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static int N, M, max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		max = 0;
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		int ans = BinarySearch();
		System.out.println(ans);
		
	}
	
	static boolean pos(int x) {
		int total = 0;
		for(int n : arr) {
			if(n > x) total += x;
			else total += n;
		}
		return total<=M ? true : false;
	}
	
	static int BinarySearch() {
		int l = 1;
		int r = max;
		while(l < r) {
			int mid = (l + r + 1) / 2;
			if(pos(mid)) {
				l = mid;
			}
			else {
				r = mid - 1;
			}
		}
		return l;
	}

}
