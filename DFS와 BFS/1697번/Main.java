import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int dist[] = new int[100001];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(N);
		dist[N] = 1;
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			
			int[] temp = new int[] {x-1, x+1, 2*x};
			for(int i=0; i<3; i++) {
				if(temp[i] < 0 || temp[i] > 100000) continue;
				if(dist[temp[i]] == 0) {
					dist[temp[i]] = dist[x] + 1;
					queue.offer(temp[i]);
				}
			}
		}
		
		System.out.println(dist[K] - 1);

	}

}
