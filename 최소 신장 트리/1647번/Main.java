import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 간선 정보 저장
		int[][] edges = new int[M][3];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken());
			edges[i][1] = Integer.parseInt(st.nextToken());
			edges[i][2] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
			
		});

		parent = new int[N+1];
		for(int i=1; i<=N; i++) parent[i] = i;
		
		int total_dist = 0;
		int max_dist = 0;
		
		for(int i=0; i<M; i++) {
			int a = edges[i][0];
			int b = edges[i][1];
			int dist = edges[i][2];
			if(find(a) != find(b)) {
				union(a, b);
				total_dist += dist;
				max_dist = Math.max(max_dist, dist);
			}
		}
		
		System.out.println(total_dist - max_dist);
		
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a > b) parent[a] = b;
		else parent[b] = a;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
}
