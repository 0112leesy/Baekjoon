import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<ArrayList<Integer>> GraphList;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer N = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		GraphList = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			GraphList.add(new ArrayList<Integer>());
		}
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			GraphList.get(a).add(b);
			GraphList.get(b).add(a);
		}
		
		dfs(1);
		
		int ans = 0;
		for(int i=1; i<=N; i++) {
			if(visited[i]) ans++;
		}
		System.out.println(ans - 1);
		
	}
	
	static void dfs(int x) {
		visited[x] = true;
		for(int y : GraphList.get(x)) {
			if(!visited[y]) {
				dfs(y);
			}
		}
		
	}

}