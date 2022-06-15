import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * 최소신장트리 - 프림 알고리즘(정점지향)
	 * 1. 시작점 지정 후 제일 가까운 지점과 연결
	 * 2. 위 집단에서 제일 가까운 지점과 연결
	 * 3. n-1번 연결될 때까지 반복
	 */

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		double[][] location = new double[n][2];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			location[i][0] = x;
			location[i][1] = y;
		}
		
		double[][] dist = new double[n][n];
		for(int i=0; i<n; i++) {
			double x = location[i][0];
			double y = location[i][1];
			for(int j=0; j<n; j++) {
				double target_x = location[j][0];
				double target_y = location[j][1];
				
				dist[i][j] = Math.sqrt(Math.pow(x-target_x, 2) + Math.pow(y-target_y, 2));
			}
		}
		
		// 개선된 프림 알고리즘
		boolean[] isblue = new boolean[n]; // 청색 정점인지 저장
		int[] near = new int[n]; // 청색 정점에 대해, 가장 가까운 적색 정점 저장
		
		for(int i=1; i<n; i++) { // 초기화 작업 (0번 정점만 적색 정점으로 지정)
			isblue[i] = true;
			near[i] = 0;
		}
		
		double total_cost = 0; // 간선 비용 총 합
		
		for(int i=1; i<n; i++) {
			double minval = Integer.MAX_VALUE;
			int newred = -1; // 이번 회차에 적색 정점으로 등록할 정점
			for(int b=0; b<n; b++) {
				if(isblue[b] && dist[b][near[b]] < minval) { // 청색 정점이면서, 간선 비용이 minval보다 작다면
					minval = dist[b][near[b]]; // minval 갱신
					newred = b; // newred 갱신
				}
			}
			isblue[newred] = false; // 탐색이 끝나면 newred를 적색 정점으로 변경
			total_cost += minval; // 간선 비용 총 합에 추가
			
			for(int b=0; b<n; b++) { // 새로운 적색 정점이 생겼으므로 near배열 갱신
                // 청색 정점이면서, 새로운 적색 정점과의 거리가 기존의 near과의 거리보다 작으면 near 갱신
				if(isblue[b] && dist[b][newred] < dist[b][near[b]]) near[b] = newred;
			}
		}
		
        // 반올림 후 출력
		System.out.println(Math.round(total_cost * 100) / 100.0);
	}

}
