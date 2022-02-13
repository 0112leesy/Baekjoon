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
			sb.append(find(Integer.parseInt(st.nextToken()))).append('\n');
		}
		System.out.println(sb);
		
	}
	
	static int find(int n) {
		int l = 0;
		int r = N-1;
		
		while(l <= r) {
			int mid = (l + r) / 2;
			
			if(n < arr.get(mid)) {
				r = mid - 1;
			}
			else if(n > arr.get(mid)) {
				l = mid + 1;
			}
			else {
				return 1;
			}
			
		}
		
		return 0;
		
	}
	
 
}