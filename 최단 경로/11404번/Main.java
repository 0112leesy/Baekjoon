import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    // 플로이드 워셜 알고리즘 사용 : 모든 정점으로의 최단 경로 탐색
	// n : 도시의 개수 : 정점의 개수
	// m : 버스의 개수 : 간선의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		// IMP = Integer.MAX_VALUE 로 설정해두면 덧셈이 일어났을 때 음수 값을 갖게 됨
		int IMP = 100000000; // 가장 비용이 많이 들었을 때 이상의 수로 설정
		int[][] Min_fee = new int[N+1][N+1];
		
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				Min_fee[i][j] = IMP;
				if(i == j) Min_fee[i][j] = 0; // 시작지점과 도착지점이 같을 때의 비용은 0
			}
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());
			
			Min_fee[from][to] = Math.min(Min_fee[from][to], amount);
		}	
		
		for(int k=1; k<=N; k++) {// k : 경유지 -> 반복문이 돌면서 1, 2, .. , N이 차례로 추가됨
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					// 새롭게 추가된 k를 경유했을 때, i -> j의 비용이 기존보다 더 낮다면 바꿔줌
					Min_fee[i][j] = Math.min(Min_fee[i][j], Min_fee[i][k] + Min_fee[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(Min_fee[i][j] == IMP) sb.append("0 ");
				else sb.append(Min_fee[i][j]).append(" ");
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		
	}
	
	
}

