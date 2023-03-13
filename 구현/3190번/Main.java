import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
		static int N, K, L;
		static Queue<Information> info;
		static boolean[][] isApple;
		static boolean[][] isSnake;
		static LinkedList<Integer[]> snake;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isApple = new boolean[N+1][N+1];
		isSnake = new boolean[N+1][N+1];
		
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) { // 사과 표시
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			isApple[r][c] = true;
		}
		
		L = Integer.parseInt(br.readLine());
		info = new LinkedList<>();

		for(int i=0; i<L; i++) { // 방향 정보 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char direction = st.nextToken().charAt(0);
			info.offer(new Information(time, direction));
		}
		
		playDummy();
		
	}
		
	static class Information {
		int time;
		char direction;
		
		Information(int time, char direction) {
			this.time = time;
			this.direction = direction;
		}
	}
	
	static void playDummy() {
		snake = new LinkedList<>();
		snake.add(new Integer[] {1, 1});
		isSnake[1][1] = true; // 게임이 시작할 때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1이다.
		int r = 1;
		int c = 1;
		
		int currentTime = 0;
		int currentDirection = 2; // 0: 위, 1: 왼쪽, 2: 오른쪽, 3: 아래
		while(true) {
			currentTime += 1;
			
			if(currentDirection == 0) {
				r -= 1;
			}
			else if(currentDirection == 1) {
				c -= 1;
			}
			else if(currentDirection == 2) {
				c += 1;
			}
			else if(currentDirection == 3) {
				r += 1;
			}
			
			if(r < 1 || c < 1 || r > N || c > N || isSnake[r][c]) { // 벽에 부딛히면 게임오버
				break;
			}
			// System.out.println(currentTime+ " > " + r + " , " + c);
			
			if(isApple[r][c]) { // 사과가 있다면
				isApple[r][c] = false; // 사과가 없어지고 꼬리는 움직이지 않음
				isSnake[r][c] = true;
				snake.add(new Integer[] {r, c});
			}
			else { // 사과가 없다면
				isSnake[r][c] = true;
				snake.add(new Integer[] {r, c});
				int delR = snake.getFirst()[0];
				int delC = snake.getFirst()[1];
				isSnake[delR][delC] = false;
				snake.removeFirst(); // 꼬리가 위치한 칸을 비워준다
			}
			
			if(!info.isEmpty() && info.peek().time == currentTime) {
				Information information = info.poll();
				if(information.time == currentTime) { // 방향 변환 O
					currentDirection = turnDirection(currentDirection, information.direction);
				}
			}
		}
		
		System.out.println(currentTime);
		
	}
	
	static int turnDirection(int current, char input) {
		switch(current) {
		case 0:
			if(input == 'L') return 1;
			if(input == 'D') return 2;
		case 1:
			if(input == 'L') return 3;
			if(input == 'D') return 0;
		case 2:
			if(input == 'L') return 0;
			if(input == 'D') return 3;
		case 3:
			if(input == 'L') return 2;
			if(input == 'D') return 1;
		}
		return -1;
	}
}