import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    // DFS 굳이 안써도 될 듯
    // 분리집합 문제 풀 때는 유니온 파인드 알고리즘 사용
	
	static int N,M;
	static char[][] map;
	static boolean[][] visited;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) map[i][j] = str.charAt(j);
		}
		
		parent = new int[N*M];
		for(int i=0; i<parent.length; i++) {
			parent[i] = i;
		}
		
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j]) DFS(i, j);
			}
		}
		
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<parent.length; i++) {
			set.add(find(i));
		}
		System.out.println(set.size());
	}
	
	static void DFS(int x, int y) {
		visited[x][y] = true;
		int newx = 0;
		int newy = 0;
		switch(map[x][y]) {
		case 'U':
			newx = x-1;
			newy = y;
			break;
		case 'D':
			newx = x+1;
			newy = y;
			break;
		case 'L':
			newx = x;
			newy = y-1;
			break;
		case 'R':
			newx = x;
			newy = y+1;
			break;
		}
		
		if(find(x * M + y) != find(newx * M + newy)) {
			union(x * M + y, newx * M + newy);
		}
		
		if(!visited[newx][newy]) {
			DFS(newx, newy);
		}
	}
	
	static int find(int x) {
		if(parent[x] == x) return parent[x];
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a > b) {
			parent[a] = b;
		}
		else parent[b] = a;
	}
}
