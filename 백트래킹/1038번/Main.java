import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int[] arr;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if(N == 0) {
			System.out.println(0);
			return;
		}
		
		for(int i=1; i<=10; i++) {
			arr = new int[i]; // i자릿수

			for(int j=1; j<=9; j++) {
				arr[0] = j;
				dfs(0, j);
				arr[0] = -1;
			}
		}
		
		if(count < N) {
			System.out.println(-1);
		}
		
	}

	static void dfs(int x, int p) { // arr[x]를 p 미만으로 할당
		if(count >= N) return;
		
		if(x == arr.length-1) {
			count++;
			if(count == N) {
				for(int n : arr) {
					System.out.print(n);
				}
			}
			return;
		}
		
		for(int i=0; i<p; i++) {
			arr[x+1] = i;
			dfs(x+1, i);
			arr[x+1] = -1;
		}
		
	}
	
}