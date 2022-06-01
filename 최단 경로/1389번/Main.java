import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
public class Main {
	static final int INF = 1000;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				graph[i][j] = INF;
				if(i == j) graph[i][j] = 0;
			}
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[A][B] = 1;
			graph[B][A] = 1;
		}
		
		// 플로이드-와셜 알고리즘
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
				}
			}
		}
        
		int min = INF;
		int CB = 0;
		for(int i=1; i<=N; i++) {
			int cul = 0;
			for(int j=1; j<=N; j++) {
				cul += graph[i][j];
			}
			if(cul < min) {
				min = cul;
				CB = i;
			}
		}
		System.out.println(CB);
	}

}
