import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	static int[][] arr;
	static String ans = "";
 
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) arr[i][j] = str.charAt(j) - '0';
		}
		func(N, 0, 0);
		System.out.println(ans);
		
	}
	static void func(int n, int x, int y) {
		int color = arr[x][y];
		for(int i=x; i<(x+n); i++) {
			for(int j=y; j<(y+n); j++) {
				if(arr[i][j] != color) {
					ans += "(";
					func(n/2, x, y);
					func(n/2, x, y+n/2);
					func(n/2, x+n/2, y);
					func(n/2, x+n/2, y+n/2);
					ans += ")";
					return;
				}
			}
		}
		ans += color;
		return;
	}

}