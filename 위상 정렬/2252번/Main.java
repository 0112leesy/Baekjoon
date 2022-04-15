import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		boolean[] check = new boolean[N+1];
		for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			if(!check[q]) check[q] = true;
			graph.get(p).add(q);
		}
		
		for(int i=1; i<=N; i++) {
			if(!check[i]) graph.get(0).add(i);
		}
		
		TopologicalSort(0);
		stack.pop();
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop()+" ");
		}
		System.out.println(sb);

		
		
	}
	
	static void TopologicalSort(int m) {
		visited[m] = true;
		
		for(int q : graph.get(m)) {
			if(!visited[q]) {
				TopologicalSort(q);
			}
		}
		
		stack.push(m);
	}

	
}
