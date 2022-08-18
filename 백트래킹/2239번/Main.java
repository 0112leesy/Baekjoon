import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] map = new int[9][9];
	static boolean end = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<9; i++) {
			String str = br.readLine();
			for(int j=0; j<9; j++) map[i][j] = str.charAt(j) - '0';
		}
		
		dfs(0);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
		
	}
	
	static void dfs(int depth) {
		
		if(depth == 81) {
			end = true;
			return;
		}
		
		int x = depth / 9;
		int y = depth % 9;
		
		if(map[x][y] != 0) { // (x,y)가 0이 아닌경우 다음 칸으로 이동
			dfs(depth + 1);
		}
		else { // (x,y)를 채워야 할 경우
			for(int i=1; i<=9; i++) { // 백트래킹으로 돌아온 후 다시 반복문을 통해 다음으로 적합한 숫자를 탐색하게 됨
				if(!check(x, y, i)) continue; // (x,y)에 숫자 i가 부적합하면 계속 탐색
				else { // 숫자 i가 적합하면 (x,y)에 넣어주기
					map[x][y] = i;
					dfs(depth + 1); // 다음 칸으로 이동
					if(end) return; // 종료 조건이 아니라면 더이상 선택할 수가 없음
					map[x][y] = 0; // 백트래킹
				}
			}
		}
	}
	
	static boolean check(int x, int y, int num) {
		for(int i=0; i<9; i++) {
			if(map[x][i] == num || map[i][y] == num) return false;
		}
		
		for(int i = x/3 * 3; i < x/3 * 3 + 3; i++) {
			for(int j = y/3 * 3; j < y/3 * 3 + 3; j++) {
				if(map[i][j] == num) return false;
			}
		}
		
		return true;
	}

}
