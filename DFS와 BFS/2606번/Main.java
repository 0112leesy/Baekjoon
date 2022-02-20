import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static boolean visit[];
	static int n, m, v;
	static int count = 0;
	
	public static int dfs(int i) {
		visit[i] = true;
		
		for(int j=1; j<=n; j++) {
			if(map[i][j] == 1 && visit[j] == false) {
				count ++;
				dfs(j);
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 컴퓨터 수(정점)
		m = Integer.parseInt(br.readLine()); // 연결된 컴퓨터 쌍의 수(간선)
		v = 1;	// 탐색 시장할 정점의 번호
		map = new int[n+1][n+1];	// 각 정점간 탐색 경로를 저장할 배열
		visit = new boolean[n+1];	// 정점의 탐색 여부 체크
		
		StringTokenizer st;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = map[b][a]= 1;
		}
		
		System.out.println(dfs(1));
	}
}