import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
 
public class Main {	
	static Integer arr[][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new Integer[N+1][N+1];
		System.out.println(BC(N, K));
	}
	
	static int BC(int N, int K) {
		if(K==N || K==0) {
			return arr[N][K] = 1;
		}
		if(arr[N][K] == null) {
            // 파스칼의 법칙 : (n+1, k+1) = (n, k) + (n, k+1)
            // 따라서 (n, k) = (n-1, k-1) + (n-1, k)
			arr[N][K] = BC(N-1, K-1) + BC(N-1, K);
		}
		return arr[N][K];
	}
 
}