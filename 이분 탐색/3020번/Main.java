import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static int N, H;
	static int[] bottoms;
	static int[] tops;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		bottoms = new int[N/2]; // 석순
		tops = new int[N/2]; // 종유석
		
		for(int i=0; i<N; i++) {
			int height = Integer.parseInt(br.readLine());
			if(i % 2 == 0) {
				bottoms[i/2] = height;
			}
			else {
				tops[i/2] = height;
			}
		}
		
		Arrays.sort(bottoms); // 오름차순 정렬 -> 뒤로 갈수록 석순의 높이가 높아짐
		Arrays.sort(tops); // 오름차순 정렬 -> 뒤로 갈수록 종유석의 높이가 높아짐
		
		int min = N;
		int count = 0;
		
		for(int i=1; i<=H; i++) {
			int conflict = binarySearch(0, N/2, i, bottoms) + binarySearch(0, N/2, H - i + 1, tops);
			if(conflict < min) {
				min = conflict;
				count = 1;
			}
			else if(conflict == min) {
				count += 1;
			}
		}
		
		System.out.println(min + " " + count);
	}
	
	static int binarySearch(int l, int r, int h, int[] arr) {
		while(l < r) {
			int mid = (l + r) / 2;
			if(arr[mid] >= h) {
				r = mid;
			}
			else {
				l = mid + 1;
			}
		}
		return arr.length-r;
	}
}