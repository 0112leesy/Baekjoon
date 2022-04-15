import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N;
	static int[][] arr, ans; // arr: 입력, ans: 내리막길로만 도달했을 때 경로의 수
	// ans[m][n] = ans[m-1][n] + ans[m+1][n] + ans[m][n-1] + ans[m][n+1]
	// 뒤의 arr 값이 크다는 전제 하에.
	static int[] dm = {-1, 0, 1, 0};
	static int[] dn = {0, -1, 0, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[M][N];
		ans = new int[M][N];
		visited = new boolean[M][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans[0][0] = 1;
		visited[0][0] = true;

		System.out.println(get_ans(M-1,N-1));
	}
	
	
	static int get_ans(int m, int n) {
		
		if(visited[m][n]) return ans[m][n];
		
		for(int i=0; i<4; i++) {
			int newm = m + dm[i];
			int newn = n + dn[i];
			if(newm < 0 || newm >= M || newn < 0 || newn >=N) continue;
			if(arr[newm][newn] > arr[m][n]) ans[m][n] += get_ans(newm, newn);
		}
		
		visited[m][n] = true;
		return ans[m][n];
		
	}
	
	
}

