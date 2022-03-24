import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<Integer> arr;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(arr);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			if(BinarySearch(Integer.parseInt(st.nextToken()))) {
				sb.append("1 ");
			}
			else {
				sb.append("0 ");
			}
		}
		System.out.println(sb);
	}
	
	static boolean BinarySearch(int x) {
		int l = 0;
		int r = N-1;
		
		while(l <= r) {
			int mid = (l+r)/2;
			if(x == arr.get(mid)) return true;
			else if(x < arr.get(mid)) {
				r = mid - 1;
			}
			else {
				l = mid + 1;
			}
		}
		return false;
	}

}
