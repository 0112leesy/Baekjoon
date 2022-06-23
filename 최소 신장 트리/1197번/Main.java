import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * 최소신장트리 - 크루스칼 알고리즘(간선지향)
	 * 1. 간선들을 가중치 순서로 오름차순 정렬
	 * 2. 선택된 최소 가중치 간선의 두 정점이 합쳐진 두 정점 사이에 있다면 버리고 아니면 추가
	 * 3. n-1번 추가될 때까지 반복
	 */
	static int[] parent;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer[]> edges = new ArrayList<>();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			edges.add(new Integer[]{A, B, C});
		}
		
		parent = new int[V+1]; // 각 노드의 부모
		int total_cost = 0; // 최소 신장 트리 가중치 합
		
		Collections.sort(edges, new Comparator<Integer[]>() {
			// 가중치 순으로 오름차순 정렬
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				return o1[2] - o2[2];
			}
			
		});
		
		for(int i=1; i<=V; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<E; i++) {
			Integer[] temp = edges.get(i);
			int A = temp[0];
			int B = temp[1];
			int C = temp[2];
            // A와 B가 같은 집합 내에 없다면 A와 B를 합쳐줌
			if(find(A) != find(B)) {
				union(A, B);
				total_cost += C;
			}
		}
		

		System.out.println(total_cost);
		
	}
	
	static void union(int a, int b) {// a와 b를 합치는 연산
		a = find(a);
		b = find(b);
		if(a > b) parent[a] = b;
		else parent[b] = a;
	}
	
	static int find(int x) {// x의 부모를 찾는 연산
		if(parent[x] == x) return x;
		else return find(parent[x]);
	}

}
