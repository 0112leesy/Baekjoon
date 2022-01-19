import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
	static int[][] arr;
	static int white = 0, blue = 0;
 
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		func(N, 0, 0);
		System.out.println(white+"\n"+blue);
		
	}

	static void func(int n, int x, int y) {
		int color = arr[x][y];
		for(int i=x; i<(x+n); i++) {
			for(int j=y; j<(y+n); j++) {
				if(arr[i][j] != color) {
					func(n/2, x, y);
					func(n/2, x, y+n/2);
					func(n/2, x+n/2, y);
					func(n/2, x+n/2, y+n/2);
					return;
				}
			}
		}
		if(color == 0) white ++;
		else blue ++;
		return;
	}
}