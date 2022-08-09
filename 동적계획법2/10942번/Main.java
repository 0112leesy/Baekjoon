import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static int[][] pal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 0: 아직모름, -1:팰린드롬 아님, 1:팰린드롬 맞음
		pal = new int[N+1][N+1];
		for(int i=1; i<=N; i++) pal[i][i] = 1;
		
		arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			if(isPal(S, E) == 1) sb.append(1).append('\n');
			else if(isPal(S, E) == -1) sb.append(0).append('\n');
		}
		System.out.println(sb);
	}
	
	static int isPal(int S, int E) {
		if(pal[S][E] != 0) return pal[S][E];
		if(E-S == 1 && arr[S] == arr[E]) {
			return pal[S][E] = 1;
		}
		if(arr[S] == arr[E] && isPal(S+1, E-1) == 1) {
			return pal[S][E] = 1;
		}
		else return pal[S][E] = -1;
	}

}
