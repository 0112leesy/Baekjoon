import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static int N,K; //  N: 체스판의 크기, K: 말의 개수
	static int[][] board, board_figure;
    // board: 해당 보드 색 (흰색/빨간색/파란색)
    // board_figure: 해당 보드의 맨 아래 말 번호
	static ArrayList<ArrayList<Figure>> graph;
    // 말이 쌓여있는 상태를 나타내는 리스트
    // i번 리스트는 i번 말을 제일 밑으로, 위에 어떤 말들이 쌓여있는지를 보여줌
    // i번 말이 맨 아래에 있는 것이 아니라면 i번 리스트는 비어있음

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {// 0:흰색, 1:빨간색, 2:파란색
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		graph = new ArrayList<>();
		board_figure = new int[N+1][N+1];
		
		for(int i=0; i<=K; i++) graph.add(new ArrayList<>());
		for(int i=1; i<=K; i++) { // i번 말을 graph의 i번 인덱스에 저장
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());		
		    // 1:오른쪽, 2:왼쪽, 3:위쪽, 4:아래쪽
			int direction = Integer.parseInt(st.nextToken());
			graph.get(i).add(new Figure(i, x, y, direction));
			board_figure[x][y] = i; // 가장 아래에 있는 말의 번호 저장
		}
		
		int ans = move_figure();
        // 종료 턴이 1000보다 크면 -1 출력
		System.out.println(ans>1000?-1:ans);
	
	}

	static class Figure{ // 말 클래스
		private int id;
		private int x;
		private int y;
		private int direction;
		
		Figure(int id, int x, int y, int direction){
			this.id = id;
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}
	
	// 번호(id) 순서대로 말 이동
	static int move_figure() {
		int cnt = 0; // 턴
		boolean flag = true; // 탈출 조건
		while(cnt++ <= 1000) {
			
			for(int i=1; i<=K; i++) { // 1번 ~ K번 말 순서대로 이동			
				
				if(graph.get(i).size() == 0) continue; // 맨 아래 말이 아니면 무시
				Figure current_figure = graph.get(i).get(0); // 맨 아래 말이면 다음 칸 좌표 얻기
				int next[] = get_next(current_figure);
				int next_x = next[0];
				int next_y = next[1];
				
				// 다음 칸이 파란색이거나 체스판을 벗어나는 경우
				if(out_of_bound(next_x, next_y) || board[next_x][next_y] == 2) {
                    // 방향 변경
					change_direction(current_figure);
					next = get_next(current_figure);
					next_x = next[0];
					next_y = next[1];
					
                    // 방향 변경 후 이동될 칸이 파란색이거나 체스판을 벗어나는 경우 무시
					if(out_of_bound(next_x, next_y) || board[next_x][next_y] == 2) continue;
					else {// 그렇지 않다면 이동
						move_figure(current_figure, next_x, next_y);
						Figure moving_figure = current_figure;
						if(board[next_x][next_y] == 1) {// 이동될 칸이 빨간색이면 말의 순서 거꾸로한 뒤 맨 위였던 말을 이동할 말로 지정
							moving_figure = reverse_figure(current_figure);
						}
						if(board_figure[next_x][next_y] == 0) {// 이동될 칸에 말이 없으면
							board_figure[next_x][next_y] = moving_figure.id;// 이동될 칸의 board_figure에 이동한 말의 id 지정
						}
						else {// 이동될 칸에 말이 있으면
							int size = put_on_figure(moving_figure, graph.get(board_figure[next_x][next_y]).get(0));
                            // 말을 얹어주고 4개 이상의 말이 쌓이면 탈출 조건 갱신
							if(size >= 4) {
								flag = false;
								break;
							}
						}
					}
				}
                // 다음 칸이 흰색 또는 빨간색인 경우
				else { // 위의 방향 변경 후 이동 로직과 동일하게 처리
					move_figure(current_figure, next_x, next_y);
					Figure moving_figure = current_figure;
					if(board[next_x][next_y] == 1) {
						moving_figure = reverse_figure(current_figure);
					}
					if(board_figure[next_x][next_y] == 0) {
						board_figure[next_x][next_y] = moving_figure.id;
					}
					else {
						int size = put_on_figure(moving_figure, graph.get(board_figure[next_x][next_y]).get(0));
						if(size >= 4) {
							flag = false;
							break;
						}
					}
				}
			}
			if(!flag) break;
		}
		return cnt;
	}
	
    // 체스판을 벗어나는 경우 true 리턴
	static boolean out_of_bound(int x, int y) {
		if(x < 1 || x > N || y < 1 || y > N) return true;
		else return false;
	}
	
    // 입력된 figure의 direction을 반대로 바꿔줌
	static void change_direction(Figure figure) {
		int current_direction = figure.direction;
		int modified_direction = 0;
		if(current_direction == 1) modified_direction = 2;
		else if(current_direction == 2) modified_direction = 1;
		else if(current_direction == 3) modified_direction = 4;
		else modified_direction = 3;
		figure.direction = modified_direction;
	}
	
    // 입력된 figure의 좌표와 direction을 바탕으로 다음 칸의 좌표를 구해줌
	static int[] get_next(Figure figure) {
		int next_x = figure.x;
		int next_y = figure.y;
		switch(figure.direction) {
		case 1:
			next_y++;
			break;
		case 2:
			next_y--;
			break;
		case 3:
			next_x--;
			break;
		case 4:
			next_x++;
			break;
		}
		return new int[]{next_x, next_y};
	}
	
    // 입력된 figure와 그 위로 쌓인 figure을 입력된 좌표로 이동
	static void move_figure(Figure figure, int next_x, int next_y) {
		board_figure[figure.x][figure.y] = 0;
		for(Figure linked_figure : graph.get(figure.id)) {
			linked_figure.x = next_x;
			linked_figure.y = next_y;
		}
	}
	
    // moving_figure을 next_figure위로 쌓아줌, next_figure에 쌓인 말의 수 리턴
	static int put_on_figure(Figure moving_figure, Figure next_figure) {
		for(Figure linked_figure : graph.get(moving_figure.id)) {
			graph.get(next_figure.id).add(linked_figure);
		}
		graph.get(moving_figure.id).clear();
		return graph.get(next_figure.id).size();
	}
	
    // bottom_figure의 말이 쌓인 순서를 반대로 변경, 변경 후 맨 아래의 figure를 리턴
	static Figure reverse_figure(Figure bottom_figure) {
        // bottom_figure이 혼자라면 순서 변경 로직 실행 X
		if(graph.get(bottom_figure.id).size()==1) return bottom_figure;
		Figure top_figure = graph.get(bottom_figure.id).get(graph.get(bottom_figure.id).size()-1);
		for(int i=graph.get(bottom_figure.id).size()-1; i>=0; i--) {
			graph.get(top_figure.id).add(graph.get(bottom_figure.id).get(i));
		}
		graph.get(bottom_figure.id).clear();
		return top_figure;
	}
}