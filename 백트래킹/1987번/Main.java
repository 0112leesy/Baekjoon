import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int R,C,cnt,max;
	static int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};
	static char[][] map;
	static boolean[][] visited;
	static Set<Character> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			char[] ch_arr = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				map[i][j] = ch_arr[j];
			}
		}
		
		// visited 배열과 set 집합을 합쳐 메모리 줄일 수 있음
		// map 방문여부가 아닌 알파벳을 선택했는지 여부를 저장하는 배열을 사용하면 됨
		visited = new boolean[R][C];
		cnt = 0;
		max = 0;
		set = new HashSet<>();
		DFS(0, 0);
		System.out.println(max);
	}
	
	static void DFS(int x, int y) {
		visited[x][y] = true;
		set.add(map[x][y]);
		cnt++;
		max = Math.max(max, cnt);
		for(int i=0; i<4; i++) {
			int newx = x+dx[i];
			int newy = y+dy[i];
			if(newx < 0 || newx >= R || newy < 0 || newy >= C) continue;
			if(!visited[newx][newy] && !set.contains(map[newx][newy])) {
				DFS(newx, newy);
			}
		}
		cnt--;
		set.remove(map[x][y]);
		visited[x][y] = false;
	}
	
}
