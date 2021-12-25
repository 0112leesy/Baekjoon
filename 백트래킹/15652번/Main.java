import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M+1];
		dfs(1);
		System.out.println(sb);
	}
	
	static void dfs(int x) {
		if(x == M+1) {
			for(int i=1; i<=M; i++) {
				sb.append(arr[i]+" ");
			}
			sb.append('\n');
		}
		else {
			for(int i=1; i<=N; i++) {
				if(arr[x] == 0 && arr[x-1] <= i) {
					arr[x] = i;
					dfs(x+1);
					arr[x] = 0;
				}
			}
		}
	}

}