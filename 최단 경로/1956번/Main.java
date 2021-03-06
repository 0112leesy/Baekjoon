import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[V+1][V+1];
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				graph[i][j] = 5000000;
				if(i==j) graph[i][j] = 0;
			}
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a][b] = c;
		}
		
		ArrayList<Integer> cycle = new ArrayList<>();
        // 플로이드-와셜 알고리즘 활용
        // 사이클을 찾는 방법을
        // dist = min(dist, graph[i][j]+graph[j][i]) 로 수정 시 속도 향상
		
		for(int k=1; k<=V; k++) {
			for(int i=1; i<=V; i++) {
				for(int j=1; j<=V; j++) {
					int current_path = graph[i][j];
					int new_path = graph[i][k] + graph[k][j];
					graph[i][j] = Math.min(current_path, new_path);
					if(current_path == 0 && new_path > 0 && new_path < 5000000) cycle.add(new_path);
				}
			}
		}

		Collections.sort(cycle);
		System.out.println(cycle.size()>0?cycle.get(0):-1);
	}
}
