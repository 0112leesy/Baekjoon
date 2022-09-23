import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R,C,N;
	static char[][] cave;
	static int[] trial;
	static int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};
	static ArrayList<Integer[]> falling_minerals;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cave = new char[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				cave[i][j] = str.charAt(j);
			}
		}
		
		N = Integer.parseInt(br.readLine());
		trial = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) trial[i] = Integer.parseInt(st.nextToken());
		
		throw_stick();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) sb.append(cave[i][j]);
			sb.append('\n');
		}
		
		System.out.println(sb);
		
	}

	// 땅과 연결된 클러스터를 미리 표시하고
	// 표시되지 않은 미네랄들은 모두 공중에 뜬 하나의 클러스터로 판단하는 로직도 있음
	static void throw_stick() {
		// 1회 시행 시 막대기를 던짐
		int cnt = 1;
		for(int height : trial) {			
			// x좌표는 R - 높이로 설정됨
			int x = R-height;
			// 막대기를 던지는 방향 확인
			boolean direction_right = cnt++%2==1? true:false;
			// 막대기를 던졌을 때 처음 닿게 되는 미네랄의 y좌표를 구함
			int y = find_first_mineral(x, direction_right);
			// y가 -1로 리턴되면 닿게 되는 미네랄이 없는 것 -> 다음 시행으로 넘어감
			if(y == -1) continue;
			
			// 처음 닿은 미네랄의 좌표를 .으로 바꿈
			cave[x][y] = '.';
			
			// 상, 하, 좌, 우 좌표에 대해
			// OOB가 아니며, 미네랄이 있으며, 땅과 닿아있는 클러스터가 아니라면 클러스터의 이동을 시행
			if(x-1 >= 0  && cave[x-1][y] == 'x' && BFS(x-1, y) != R-1) { // 위쪽 클러스터의 이동
				mineral_fall(x-1, y);
			}
			else if(y-1 >= 0 && cave[x][y-1] == 'x' && BFS(x, y-1) != R-1) { // 왼쪽 클러스터의 이동
				mineral_fall(x, y-1);
			}
			else if(y+1 < C && cave[x][y+1] == 'x' && BFS(x, y+1) != R-1) { // 오른쪽 클러스터의 이동
				mineral_fall(x, y+1);
			}
			else if(x+1 < R && cave[x+1][y] == 'x' && BFS(x+1, y) != R-1) { // 아래쪽 클러스터의 이동
				mineral_fall(x+1, y);
			}
			
		}
	}
	
	static int find_first_mineral(int height, boolean direction_right) {
		if(direction_right) {
			for(int i=0; i<C; i++) {
				if(cave[height][i] == 'x') return i;
			}
		}
		else {
			for(int i=C-1; i>=0; i--) {
				if(cave[height][i] == 'x') return i;
			}
		}
		return -1;
	}
	
	static int BFS(int x, int y) { // 클러스터는 BFS 탐색으로 구함
		// (x,y)좌표와 같은 클러스터인 미네랄의 좌표를 falling_minerals에 저장
		// 이때 제일 큰 x좌표 값을 max_row_num에 저장
		// max_row_num이 R-1이면 클러스터가 땅과 닿아있다는 뜻임
		falling_minerals = new ArrayList<>();
		int max_row_num = x;
		boolean[][] visited = new boolean[R][C];
		Queue<Integer[]> queue = new LinkedList<>();
		
		visited[x][y] = true;
		queue.offer(new Integer[] {x, y});
		falling_minerals.add(new Integer[] {x, y});
		
		while(!queue.isEmpty()) {
			Integer[] coordinate = queue.poll();
			int nowx = coordinate[0];
			int nowy = coordinate[1];
			
			for(int i=0; i<4; i++) {
				int newx = nowx + dx[i];
				int newy = nowy + dy[i];
				
				if(newx < 0 || newx >= R || newy < 0 || newy >= C) continue;
				if(!visited[newx][newy] && cave[newx][newy] == 'x') {
					visited[newx][newy] = true;
					queue.offer(new Integer[] {newx, newy});
					falling_minerals.add(new Integer[] {newx, newy});
					if(max_row_num < newx) max_row_num = newx;
				}
			}
		}
		return max_row_num;
	}

	static void mineral_fall(int x, int y) {
		// falling_coor의 모든 좌표들을 .로 처리해줌 (이동할 것이기 때문)
		for(Integer[] falling_coor : falling_minerals) {
			cave[falling_coor[0]][falling_coor[1]] = '.';
		}
		
		boolean stop = false;
		while(!stop) {
			// 모든 falling_coor 좌표들이 아래로 한 칸 내려갈 수 있는지 확인
			for(Integer[] falling_coor : falling_minerals) {
				int fall_x = falling_coor[0];
				int fall_y = falling_coor[1];
				if(fall_x == R-1 || cave[fall_x+1][fall_y] == 'x') {
					// 떨어질 곳의 x좌표가 땅보다 낮거나 미네랄이 있다면 stop하도록 함
					stop = true;
					break;
				}
			}
			if(stop) continue;
			// 모든 좌표들이 한 칸 내려갈 수 있는지 보장이 됐다면 좌표 1칸 갱신 후 다시 확인 작업 반복
			for(int i=0; i<falling_minerals.size(); i++) {
				falling_minerals.get(i)[0] += 1;
			}
		}
		// 좌표 갱신이 완료되면 cave의 상태를 업데이트 해줌
		for(int i=0; i<falling_minerals.size(); i++) {
			cave[falling_minerals.get(i)[0]][falling_minerals.get(i)[1]] = 'x';
		}
		
	}
}