import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, H;
	static ArrayList<ArrayList<Integer>> students;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		students = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			ArrayList<Integer> student = new ArrayList<>();
			students.add(student);
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				students.get(i+1).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		dp = new int[N+1][1001];
		for(int i=0; i<=N; i++) {
			dp[i][0] = 1;
		}
		
		runDp();
	}
	
	static void runDp() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=H; j++) {
				for(Integer block : students.get(i)) {
					if(j - block >= 0) {
						dp[i][j] += dp[i-1][j-block];
						dp[i][j] %= 10007;
					}
				}
				dp[i][j] += dp[i-1][j];
				dp[i][j] %= 10007;
			}
		}
		
		System.out.println(dp[N][H]);
	}
}