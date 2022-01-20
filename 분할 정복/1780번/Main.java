import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	static int[][] arr;
	static int neg = 0, zero = 0, pos = 0;
 
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
		System.out.println(neg);
		System.out.println(zero);
		System.out.println(pos);
		
	}
	static void func(int n, int x, int y) {
		int paper = arr[x][y];
		for(int i=x; i<(x+n); i++) {
			for(int j=y; j<(y+n); j++) {
				if(arr[i][j] != paper) {
					n /= 3;
					func(n, x, y);
					func(n, x, y+n);
					func(n, x, y+2*n);
					func(n, x+n, y);
					func(n, x+n, y+n);
					func(n, x+n, y+2*n);
					func(n, x+2*n, y);
					func(n, x+2*n, y+n);
					func(n, x+2*n, y+2*n);
					return;
				}
			}
		}
		switch(paper) {
		case -1:
			neg ++;
			break;
		case 0:
			zero ++;
			break;
		case 1:
			pos ++;
			break;
		}
		return;
	}

}