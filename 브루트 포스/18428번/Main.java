import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static char[][] hall;
	static boolean[] visited;
	static boolean[] isEmpty;
	static List<List<Coord>> objectCoords;
	static List<Integer[]> teachers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		hall = new char[N][N];
		teachers = new ArrayList<>();
		isEmpty = new boolean[N*N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				hall[i][j] = st.nextToken().charAt(0);
				if(hall[i][j] == 'T') {
					teachers.add(new Integer[] {i, j});
				}
				else if(hall[i][j] == 'X') {
					isEmpty[i * N + j] = true;
				}
			}
		}
		
		visited = new boolean[N*N];
		objectCoords = new ArrayList<>();
		getObjectCoord(0, 0);
	
		String answer = "NO";
		for(List<Coord> coords : objectCoords) {
			if(isCovered(coords)) {
				answer = "YES";
				break;
			}
		}
		
		System.out.println(answer);
		
	}
	
	static class Coord {
		private int x;
		private int y;
		
		Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static void getObjectCoord(int index, int depth) {
		if(depth == 3) {
			List<Coord> tmp = new ArrayList<>();
		
			for(int i=0; i<N*N; i++) {
				if(visited[i]) {
					tmp.add(new Coord(i / N, i % N));
				}
				if(tmp.size() == 3) break;
			}
			
			objectCoords.add(tmp);
			return;
		}
		
		for(int i=index; i<N*N; i++) {
			if(!visited[i] && isEmpty[i]) {
				visited[i] = true;
				getObjectCoord(i+1, depth+1);
				visited[i] = false;
			}
		}
	}
	
	static boolean isCovered(List<Coord> coords) {
		for(Coord coord : coords) {
			hall[coord.x][coord.y] = 'O';
		}
		
		boolean flag = true;
		for(Integer[] teacher : teachers) {
			if(!checkTeacher(teacher[0], teacher[1])) {			
				flag = false;
				break;
			}
		}
		
		for(Coord coord : coords) {
			hall[coord.x][coord.y] = 'X';
		}
		return flag;
	}
	
	static boolean checkTeacher(int tx, int ty) {
		// 상
		int top_tx = tx;
		while(--top_tx >= 0) {
			if(hall[top_tx][ty] == 'O') {
				break;
			}
			if(hall[top_tx][ty] == 'S') {
				return false;
			}
		}
		
		// 하
		int bot_tx = tx;
		while(++bot_tx < N) {
			if(hall[bot_tx][ty] == 'O') {
				break;
			}
			if(hall[bot_tx][ty] == 'S') {
				return false;
			}
		}
		
		// 좌
		int left_ty = ty;
		while(--left_ty >= 0) {
			if(hall[tx][left_ty] == 'O') {
				break;
			}
			if(hall[tx][left_ty] == 'S') {
				return false;
			}
		}
		
		// 우
		int right_ty = ty;
		while(++right_ty < N) {
			if(hall[tx][right_ty] == 'O') {
				break;
			}
			if(hall[tx][right_ty] == 'S') {
				return false;
			}
		}
		
		return true;
	}
	
}