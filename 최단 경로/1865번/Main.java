import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, W;
	static ArrayList<Edge> edges;
	static final int INF = 6000000;
	
	static class Edge {
		int start;
		int end;
		int time;
		
		Edge(int start, int end, int time) {
			this.start = start;
			this.end = end;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(TC -- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			edges = new ArrayList<>();
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				
				boolean updated = false;
				for(Edge edge : edges) {
					if(edge.start == start && edge.end == end) {
						edge.time = Math.min(edge.time, time);
						updated = true;
					}
					if(edge.end == start && edge.start == end) {
						edge.time = Math.min(edge.time, time);
						updated = true;
					}
				}

				if(!updated) {
					edges.add(new Edge(start, end, time));
					edges.add(new Edge(end, start, time));
				}	
			}
			
			for(int i=0; i<W; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				
				boolean updated = false;
				for(Edge edge : edges) {
					if(edge.start == start && edge.end == end) {
						edge.time = Math.min(edge.time, -time);
						updated = true;
						break;
					}
				}

				if(!updated) {
					edges.add(new Edge(start, end, -time));
				}
			}
			
			sb.append(getAnswer()).append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	static String getAnswer() {
		
		for(int i=1; i<=N; i++) {
			// System.out.println(i + "를 출발 노드로 하여 탐색");
			
			int[] distance = new int[N+1];
			Arrays.fill(distance, INF);
			distance[i] = 0;
			
			for(int j=1; j<=N; j++) {
				boolean update = false;
				for(Edge edge : edges) {
					if(distance[edge.start] != INF && distance[edge.end] > distance[edge.start] + edge.time) {
						distance[edge.end] = distance[edge.start] + edge.time;
						update = true;
						// System.out.println(edge.end+ " distance updated : " + distance[edge.end]);
						if(j == N) return "YES";

					}
				}
				// 밸만 포드 알고리즘에서 시간 단축을 위한 핵심
				// 모든 edge를 돌며 확인했을 때 값 갱신이 이루어지지 않으면 탐샘을 종료한다
				if(!update) break;
			}
		}
		
		return "NO";
		
	}
}