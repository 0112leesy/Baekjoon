import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N == K) {
			System.out.println(0);
			return;
		}
		
		int[] visited = new int[100001]; // 방문 가능한 지점: 0, 1, ..., 100000
		Arrays.fill(visited, -1);
		
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(N);
		visited[N] = 0;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			if(now == K) {
				System.out.println(visited[now]);
				break;
			}
			
			int next = 0;
			int visited_next = 0;
			for(int i=0; i<3; i++) { // 순간이동을 하는 경우를 먼저 탐색
				if(i == 0) {
					next = now * 2;
					visited_next = visited[now];
				}
				else if(i == 1) {
					next = now + 1;
					visited_next = visited[now]+1;
				}
				else if(i == 2) {
					next = now - 1;
					visited_next = visited[now]+1;
				}

				if(next < 0 || next > 100000) continue; // OOB
				// 방문 하지 않았거나 기존 방문시간보다 현재 방문시간이 더 빠른 경우 방문 시간 업데이트
				if(visited[next] == -1 || visited[next] > visited_next) {
					visited[next] = visited_next;
					queue.offer(next);
				}
			}
		}
	}
}