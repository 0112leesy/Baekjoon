import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		// 오름차순으로 특성값 정렬
		Arrays.sort(arr);
		
		// 양쪽 끝에 시작과 끝을 둠
		int l = 0;
		int r = arr.length-1;
		int diff = Integer.MAX_VALUE;
		int liq1 = arr[l];
		int liq2 = arr[r];
		
		while(l < r) {
			if(Math.abs(arr[l] + arr[r]) < diff) { // 현재 절댓값이 기존 절댓값보다 작으면 갱신
				liq1 = arr[l];
				liq2 = arr[r];
				diff = Math.abs(liq1 + liq2);
			}
			if(arr[l] + arr[r] >= 0) { // 합이 0보다 크면 값을 줄이기 위해 r 이동
				r--;
			}
			else if(arr[l] + arr[r] < 0) { // 합이 0보다 작으면 값을 키우기 위해 l 이동
				l++;
			}
		}
		
		System.out.println(liq1+" "+liq2);
	}
	
}
