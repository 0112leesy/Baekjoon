import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[] items = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
        // graph 배열 초기화
		int[][] graph = new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(i == j) graph[i][j] = 0;
				else graph[i][j] = 16;
			}
		}
		
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			graph[a][b] = graph[b][a] = l;
		}
		
		// 모든 정점들을 한 번에 하나씩 경유지 정점들의 집합에 추가
		for(int k=1; k<=n; k++) { // 경유지
			for(int i=1; i<=n; i++) { // 출발지
				for(int j=1; j<=n; j++) { // 도착지
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		// 플로이드-와셜 알고리즘으로 모든 정점 사이의 최단거리 탐색 종료 후
		// 수색 범위 내에서 획득 가능한 아이템 수 확인
		
		int ans = 0;
		for(int i=1; i<=n; i++) { // i 지점에 낙하했을 때
			int cnt = 0;
			for(int j=1; j<=n; j++) { // j 지점이 수색 가능하다면 해당 지점 아이템 수 추가
				if(graph[i][j] <= m) cnt += items[j];
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);

	}

		
}

