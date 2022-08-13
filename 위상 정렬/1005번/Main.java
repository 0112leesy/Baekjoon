import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 건물의 개수
			int K = Integer.parseInt(st.nextToken()); // 건설순서 규칙
			
			int[] delay = new int[N+1]; // 건물당 건설 소요시간
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) delay[i] = Integer.parseInt(st.nextToken());
			
			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
			for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
			int[] in_edge = new int[N+1]; // 진입 차수
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				in_edge[to]++;
				graph.get(from).add(to);
			}
			
			int W = Integer.parseInt(br.readLine()); // 건설해야 할 건물의 번호
			
			int[] result = new int[N+1]; // result[i]: i 건물을 짓는데 소요되는 최소 시간
			Queue<Integer> queue = new LinkedList<>();
			for(int i=1; i<=N; i++) {
				result[i] = delay[i]; // 건물 당 소요시간으로 초기화
				if(in_edge[i] == 0) queue.offer(i); // 진입차수가 0인 건물부터 큐에 삽입
			}
			
			// 위상정렬 + DP
			while(!queue.isEmpty()) {
				int x = queue.poll();
				for(int connected : graph.get(x)) {
					// 현재 건물 이후에 지을 수 있는 건물의 최소 소요시간 갱신
					result[connected] = Math.max(result[connected], result[x]+delay[connected]);
					in_edge[connected]--;
					// 진입 차수가 0이 되면 다시 큐에 삽입
					if(in_edge[connected] == 0) queue.offer(connected);
				}

			}
			sb.append(result[W]).append('\n');
		}
		System.out.println(sb);
	}

}
