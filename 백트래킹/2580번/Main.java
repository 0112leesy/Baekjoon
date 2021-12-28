import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
 
	static int[][] arr = new int[9][9];
	
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0; i<9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		sudoku(0,0);
		
	}
	
	static boolean pos(int row, int col, int val) {
		// 행 탐색
		for(int i=0; i<9; i++) {
			if(arr[row][i] == val) return false;
		}
		
		// 열 탐색
		for(int i=0; i<9; i++) {
			if(arr[i][col] == val) return false;
		}
		
		// 3*3 칸 탐색
		int start_row = row / 3 * 3;
		int start_col = col / 3 * 3;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) if(arr[start_row+i][start_col+j] == val) return false;
		}
		
		return true;
	}
	
	static void sudoku(int row, int col) {
		if(col == 9) { // 행이 모두 채워지면 다음 행 채우기
			sudoku(row+1, 0);
			return; 
		}
		
		if(row == 9) { // 스도쿠가 모두 채워진 경우
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(arr[i][j]+" ");
				}
				sb.append('\n');
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		if(arr[row][col] == 0) { // 빈 칸일 때 가능한 수 탐색
			for(int i=1; i<=9; i++) {
				if(pos(row, col, i)) {
					arr[row][col] = i;
					sudoku(row, col+1); // 행부터 쭉 채움
				}
			}
			arr[row][col] = 0; // 백트래킹
			return;
		}
		
		sudoku(row, col+1);
		
	}
 
}