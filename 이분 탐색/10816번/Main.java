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
			int n = Integer.parseInt(st.nextToken());
			int ans = find_right(n) - find_left(n);
			sb.append(ans+" ");
			
		}
		System.out.println(sb);
		
	}
	
	static int find_left(int n) { // n값이 처음 위치하는 곳 찾기
		
		int l = 0;
		int r = N; // N까지 탐색
		
		while(l < r) {
			int mid = (l+r)/2;
			
			if(n <= arr.get(mid)) {
				r = mid;
			}
			else if(n > arr.get(mid)) {
				l = mid+1;
			}
			
		}
		
		return l;
		
	}
	
	static int find_right(int n) { // n초과의 값이 처음 위치하는 곳 찾기
		
		int l = 0;
		int r = N; // N까지 탐색
		
		while(l < r) {
			int mid = (l+r)/2;
			
			if(n < arr.get(mid)) {
				r = mid;
			}
			else if(n >= arr.get(mid)) {
				l = mid+1;
			}

		}
		
		return r;
	}
 
}