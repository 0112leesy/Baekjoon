import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static int N,C,ans;
	static Location[] field;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		field = new Location[N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			field[i] = new Location(x, y);
		}

		Kruskal();
		System.out.println(ans);
	}
	
	static class Location{
		private int x;
		private int y;
		
		Location(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int get_distance(Location L1, Location L2) {
		return (int)(Math.pow(L1.x-L2.x, 2) + Math.pow(L1.y-L2.y, 2));
	}
	// 크루스칼 알고리즘
	static void Kruskal() {
		parents = new int[N+1];
		for(int i=0; i<=N; i++) parents[i] = i;
		
		ArrayList<int[]> edges = new ArrayList<>();
		for(int i=1; i<N; i++) {
			for(int j=i+1; j<=N; j++) {
				edges.add(new int[] {i, j, get_distance(field[i], field[j])});
			}
		}
		
		// sort 대신 우선순위큐 사용 시 시간 단축
		Collections.sort(edges, new Comparator<int[]>(){

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2] - o2[2];
			}
			
		});
		
		int cnt = 0;
		for(int i=0; i<edges.size(); i++) {
			int a = edges.get(i)[0];
			int b = edges.get(i)[1];
			int cost = edges.get(i)[2];
			
			if(cost < C) continue;
			
			if(find(a) != find(b)) {
				union(a, b);
				ans += cost;
				cnt++;
			}
			if(cnt == N-1) break;
		}
		if(cnt != N-1) ans = -1;
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a > b) parents[a] = b;
		else parents[b] = a;
	}
	
	static int find(int x) {
		if(parents[x] == x) return parents[x];
		return parents[x] = find(parents[x]);
	}
}