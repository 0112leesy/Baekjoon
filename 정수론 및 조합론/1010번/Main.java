import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
 
public class Main {	
	static Integer arr[][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			arr = new Integer[n+1][k+1];
			sb.append(BC(n,k)).append("\n");
		}
		System.out.println(sb);
	}
	
	static int BC(int N, int K) {
		if(K==N || K==0) {
			return arr[N][K] = 1;
		}
		if(arr[N][K] == null) {
			arr[N][K] = BC(N-1, K-1) + BC(N-1, K);
		}
		return arr[N][K];
	}
 
}