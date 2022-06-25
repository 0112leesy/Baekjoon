import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	/* 서로소 집합을 이용한 크루스칼 알고리즘 활용
	 * 1. 거리가 작은 순으로 간선 고려
	 * 2. find 연산 값이 다르면 union 해줌
	 */
	static int[] parent;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 위치 저장
		int[][] location = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			location[i][0] = x;
			location[i][1] = y;
		}
		
		// 위치에 따른 각각의 거리 저장
		double[][] distance = new double[N][N];
		for(int i=0; i<N; i++) {
			int my_x = location[i][0];
			int my_y = location[i][1];
			for(int j=0; j<N; j++) {
				if(i == j) continue;
				int other_x = location[j][0];
				int other_y = location[j][1];
				distance[i][j] = Math.sqrt(Math.pow(my_x - other_x, 2) + Math.pow(my_y - other_y, 2));
			}
		}
		
		// 간선 정보 저장 [a, b, distance]
		double[][] edges = new double[N*(N-1)/2][3];
		int edge_cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				edges[edge_cnt][0] = i;
				edges[edge_cnt][1] = j;
				edges[edge_cnt][2] = distance[i][j];
				edge_cnt++;
			}
		}
		
		// 간선을 distance 기준 오름차순 정렬
		Arrays.sort(edges, new Comparator<double[]>() {

			@Override
			public int compare(double[] o1, double[] o2) {
				if (o1[2] - o2[2] > 0) return 1;
				else if(o1[2] == o2[2]) return 0;
				else return -1;
			}
			
		});

		// parent 배열 초기화
		parent = new int[N];
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}
		
		// 이미 연결된 정보 저장 - 주어진 간선에 대해 union 연산을 해줌
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			union(a, b);
		}
		
		double total_dist = 0; // 간선이 더해짐에 따라 누적된 거리 - 최소 통로 길이가 됨
		for(int i=0; i<edges.length; i++) {
			int a = (int)edges[i][0];
			int b = (int)edges[i][1];
			double dist = edges[i][2];
			
			if(find(a) != find(b)) {
				union(a,b);
				total_dist += dist;
			}
		}
		
		// String.format 함수를 활용해 소수점 둘째자리까지 출력
		System.out.println(String.format("%.2f", total_dist));
		
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a > b) parent[a] = b;
		else parent[b] = a;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
}
