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
		System.out.println(BC(N, K) % 10007);
	}
	
	static int BC(int N, int K) {
		if(K==N || K==0) {
			return arr[N][K] = 1;
		}
		if(arr[N][K] == null) {
			arr[N][K] = BC(N-1, K-1) + BC(N-1, K);
		}
		return arr[N][K] % 10007;
	}
 
}