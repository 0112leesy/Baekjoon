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
		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int[] floor = new int[F+1];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(S);
		floor[S] = 1;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(now == G) {
				break;
			}
			int nextU = now + U;
			int nextD = now - D;
			if(nextU <= F && floor[nextU] == 0) {
				queue.offer(nextU);
				floor[nextU] = floor[now] + 1;
			}
			if(nextD > 0 && floor[nextD] == 0) {
				queue.offer(nextD);
				floor[nextD] = floor[now] + 1;
			}
		}
		
		System.out.println(floor[G]==0?"use the stairs":floor[G]-1);
	}
	
}
