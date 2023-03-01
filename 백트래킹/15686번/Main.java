import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] field;
	static boolean[] visited;
	static ArrayList<Chicken> chickens;
	static ArrayList<House> houses;
	static int optDistance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		chickens = new ArrayList<>();
		houses = new ArrayList<>();
		
		field = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
				if(field[i][j] == 2) {
					chickens.add(new Chicken(i, j));
				}
				if(field[i][j] == 1) {
					houses.add(new House(i, j));
				}
			}
		}

		visited = new boolean[chickens.size()];
		optDistance = Integer.MAX_VALUE;
		Combination(0, 0);
		
		System.out.println(optDistance);
		
	}
	
	static class Chicken {
		int r;
		int c;
		
		Chicken(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class House {
		int r;
		int c;
		
		House(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void Combination(int start, int cnt) {

		if(cnt == M) {
			// 도시 치킨 거리 구하기
			int cityChickenDistance = getCityChickenDistance();
			// 최적 거리보다 작다면 갱신
			if(cityChickenDistance < optDistance) {
				optDistance = cityChickenDistance;
			}
			return;
		}
		
		for(int i=start; i<chickens.size(); i++) {
			visited[i] = true;
			Combination(i+1, cnt+1);
			visited[i] = false;
		}
	}
	
	static int getCityChickenDistance() {
		int cityChickenDistance = 0;
		for(House house : houses) {
			int minDistance = Integer.MAX_VALUE;
		
			for(int i=0; i<chickens.size(); i++) {
				if(visited[i]) {
					Chicken chicken = chickens.get(i);
					int distance = Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
					minDistance = Math.min(minDistance, distance);
				}
			}
			cityChickenDistance += minDistance;
		}
		return cityChickenDistance;
	}
	
//	static int[] dx = {-1, 0, 0, 1};
//	static int[] dy = {0, -1, 1, 0};
	
	// 치킨 거리를 구하기 위해 최단 거리 계산을 할 BFS 함수를 만들었지만 시간초과
	// 치킨집이 최대 13개밖에 없으므로 그냥 하나씩 비교해서 치킨 거리를 찾아야 했다
	// 문제에 확실히 작은 수가 명시되어 있으면 꼭 써먹자
	// 그리고 조합 구현 코드는 꼭 기억해두기 - 완전 탐색에서 자주 쓰이는 듯
//	static int BFS(int x, int y, int[][] removedField) {
//		int[][] distance = new int[N+1][N+1];
//		Queue<Integer[]> queue = new LinkedList<>();
//		
//		distance[x][y] = 1;
//		queue.offer(new Integer[] {x, y});
//		
//		int chickenDistance = 0;
//		while(!queue.isEmpty()) {
//			Integer[] now = queue.poll();
//			int nowx = now[0];
//			int nowy = now[1];
//			
//			for(int i=0; i<4; i++) {
//				int newx = nowx + dx[i];
//				int newy = nowy + dy[i];
//				
//				if(newx < 1 || newx > N || newy < 1 || newy > N || distance[newx][newy] != 0) continue;
//				if(removedField[newx][newy] == 2) {
//					chickenDistance = distance[nowx][nowy];
//					return chickenDistance;
//				}
//				distance[newx][newy] = distance[nowx][nowy] + 1;
//				queue.offer(new Integer[] {newx, newy});
//			}
//		}
//		
//		return chickenDistance;
//	}
	
}