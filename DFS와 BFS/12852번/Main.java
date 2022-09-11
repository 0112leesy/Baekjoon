import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	/*
	 * 1. x가 3으로 나누어 떨어지면, 3으로 나눈다
	 * 2. x가 2로 나누어 떨어지면, 2로 나눈다
	 * 3. 1을 뺀다
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[1000001];
		int[] parent = new int[1000001];
		for(int i=1; i<=1000000; i++) parent[i] = i;
		
		queue.offer(N);
		visited[N] = 1;
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			
			if(x == 1) {
				System.out.println(visited[x]-1);
				break;
			}
			
			if(visited[x-1] == 0) {
				queue.offer(x-1);
				visited[x-1] = visited[x]+1;
				parent[x-1] = x;
			}
			if(x % 3 == 0 && visited[x/3] == 0) {
				queue.offer(x/3);
				visited[x/3] = visited[x]+1;
				parent[x/3] = x;
			}
			if(x % 2 == 0 && visited[x/2] == 0) {
				queue.offer(x/2);
				visited[x/2] = visited[x]+1;
				parent[x/2] = x;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int x = 1;
		sb.append(x);
		while(parent[x] != x) {
			sb.insert(0, parent[x]+" ");
			x = parent[x];
		}
		System.out.println(sb);
	}
}
