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
		Arrays.sort(arr); // 연속성을 위한 정렬
		int X = Integer.parseInt(br.readLine());
		
		if(N == 1 && X == 1) {
			System.out.println(0);
			return;
		}
		
		int l = 0;
		int r = arr.length-1;
		int cnt = 0;
		
		while(l < r) {
			int sum = arr[l] + arr[r];
			if(sum == X) cnt++;
			if(sum <= X) l++;
			else if(sum > X) r--;
		}
		
		System.out.println(cnt);
	}

	

}
