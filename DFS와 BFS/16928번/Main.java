import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static Map<Integer, Integer> hmap;
	static int[] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		hmap = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			hmap.put(x, y);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			hmap.put(u, v);
		}
		
		visited = new int[101];
		visited[1] = 1;
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			if(temp == 100) break;
			
			for(int i=1; i<=6; i++) {
				int next = temp+i;
				if(next > 100) continue;
				if(hmap.containsKey(next)) {
					int map_next = hmap.get(next);
					if(visited[map_next] == 0) {
						visited[map_next] = visited[temp]+1;
						queue.offer(map_next);
					}
				}
				else {
					if(visited[next] == 0) {
						visited[next] = visited[temp]+1;
						queue.offer(next);
					}
				}
			}
		}
		System.out.println(visited[100]-1);
	}
}
