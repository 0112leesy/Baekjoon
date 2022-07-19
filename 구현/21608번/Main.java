import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * 1. 비어있는 칸인지 확인
	 * 2. 인접 칸에 좋아하는 학생 및 빈 칸의 수를 구함 -> 기존보다 더 클 때 갱신
	 * 3. 구해진 좌표에 해당 학생 배정
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashMap<Integer, ArrayList<Integer>> list = new HashMap<>();
		
		int[] key_set = new int[N*N];
		for(int i=0; i<N*N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			key_set[i] = key;
			list.put(key, new ArrayList<>());
			for(int j=0; j<4; j++) list.get(key).add(Integer.parseInt(st.nextToken()));
		}

		int[][] map = new int[N][N];
		int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};
		
		long score = 0;
		
		for(int i=0; i<N*N; i++) {
			ArrayList<Integer> tmp = list.get(key_set[i]);
			int self = key_set[i];
			int likes = -1;
			int blanks = -1;
			int get_x = -1;
			int get_y = -1;
			for(int x=0; x<N; x++) { // 탐색 시작
				for(int y=0; y<N; y++) {
					if(map[x][y] == 0) { // 현재 칸이 비어있는 칸 일때
						int cnt_likes = 0;
						int cnt_blanks = 0;
						for(int j=0; j<4; j++) { // 인접 칸에 대해 조사
							if(x+dx[j]<0 || x+dx[j]>=N || y+dy[j]<0 || y+dy[j]>=N) continue;
							if(map[x+dx[j]][y+dy[j]] == 0) cnt_blanks++;
							else if(tmp.contains(map[x+dx[j]][y+dy[j]])) cnt_likes++;
						}
						if(cnt_likes > likes) { // 좋아하는 학생 수 갱신
							likes = cnt_likes;
							blanks = cnt_blanks;
							get_x = x;
							get_y = y;
						}
						else if(cnt_likes == likes && cnt_blanks > blanks) { // 빈칸 수 갱신
							blanks = cnt_blanks;
							get_x = x;
							get_y = y;
						}
					}
				}
			} // 구해진 좌표에 배정
			map[get_x][get_y] = self;
		}
        
		// 만족도 계산
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int key = map[i][j];
				ArrayList<Integer> tmp = list.get(key);
				int cnt = 0;
				for(int k=0; k<4; k++) {
					if(i+dx[k]<0 || i+dx[k]>=N || j+dy[k]<0 || j+dy[k]>=N) continue;
					if(tmp.contains(map[i+dx[k]][j+dy[k]])) cnt++;
				}
				if(cnt>0) score += Math.pow(10, cnt-1);
			}
		}
		System.out.println(score);
	}
}
