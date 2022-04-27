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
		
		int[] visited = new int[100001]; // 방문한 시간 저장
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(N); // N에서 탐색 시작
		visited[N] = 1; // N의 방문 시간은 1로 설정
		
		// K < N인 경우 -1로 한 칸씩 움직이는 방법밖에 없음
		if(K <= N) {
			System.out.println(N-K);
			System.out.println(1);
			return;
		}
		
		int min = 1000000; // K를 방문한 최소 시간 => BFS에서 최초로 탐색된 시간
		int cnt = 0; // 최소 시간으로 방문한 경우의 수 => 중복 탐색을 허용하여야 함
		
		while(!queue.isEmpty()) {
			int current = queue.poll(); // 현재 탐색 위치
			
			if(min < visited[current]) continue; // K를 방문한 최소 시간보다 현재 시간이 더 크면 무시함

			int next = 0; // 다음에 방문할 위치
			for(int i=0; i<3; i++) { // 3가지 경우 고려
				if(i == 0) next = current-1;
				else if (i == 1) next = current+1;
				else next = 2*current;
				
				if(next < 0 || next > 100000) continue; // OOB
				
				if(next == K) { // 다음에 방문할 위치가 K인 경우 min값에 현재 시간 저장
					min = visited[current];
					cnt++; // 경우의 수 ++
				}
				
				// 다음에 방문할 위치가 아직 방문되지 않았거나
				// 다음에 방문할 위치가 기존 방문 시간과 같은 경우 -> 최소 시간에 방문할 수 있는 경우의 수 확보를 위함
				// 방문을 진행한다
				if(visited[next] == 0 || visited[next] == visited[current]+1) {
					queue.offer(next);
					visited[next] = visited[current] + 1;
				}
			}
			
			
		}
		System.out.println(min);
		System.out.println(cnt);
	}

}

