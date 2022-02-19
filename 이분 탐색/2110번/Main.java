import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
	
	static int[] arr;
	static int N, C;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());
		Arrays.sort(arr); // 좌표 정렬
		
		int l = 1;
		int r = arr[N-1] - arr[0];
		// 가장 인접한 두 공유기 사이의 최대 거리를 찾기 위한 이분탐색
		// mid의 거리로 공유기 설치가 충분하다면 mid 이상의 범위에서 탐색
		// mid의 거리로 공유기 설치가 부족하다면 mid 미만의 범위에서 탐색
		while(l < r) {
			int mid = (l + r + 1) / 2;
			if(check(mid)) l = mid;
			else r = mid - 1;
		}
		
		System.out.println(l);
		
	}
	
	static boolean check(int x) {
		// x가 가장 인접한 두 공유기 사이의 최대 거리 일 때,
		// C개의 공유기가 범위 내에 설치가 가능한지 확인
		int now = arr[0];
		int cnt = 1;
		
		for(int i=1; i<N; i++) {
			if(arr[i] >= now + x) {
				now = arr[i];
				cnt++;
			}
		}
		
		if(cnt >= C) return true;
		else return false;
	}
}