import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K;
	static ArrayList<Fireball> Fireballs;
	static ArrayList<Integer>[][] Map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Map = new ArrayList[N+1][N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(Map[i], new ArrayList<>());
		}
		
		Fireballs = new ArrayList<>();
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(br.readLine());
			int c = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			int s = Integer.parseInt(br.readLine());
			int d = Integer.parseInt(br.readLine());
			
			Fireballs.add(new Fireball(i, r, c, m, s, d));
			Map[r][c].add(i);
		}
		
		/*
		 * 1. 파이어볼이 움직인다
		 * 2. 같은 칸에 있는 파이어볼은 합친다
		 * 3. 합쳐진 파이어볼은 4개로 나뉜다
		 */
		while(K-- > 0) {
			// 1. 파이어볼이 움직인다
			M = Fireballs.size();
			for(int i=0; i<M; i++) {
				if(Fireballs.get(i) == null) continue;
				Fireball Fb = Fireballs.get(i);
				Map[Fb.r][Fb.c].remove(i);
				Fb.move();
				Map[Fb.r][Fb.c].add(i);
			}
			
			// 2. 같은 칸에 있는 파이어볼은 합친다
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(Map[i][j].size() > 1) {
						
						int direction_stat = Fireballs.get(Map[i][j].get(0)).d % 2;
						
						int total_m = 0;
						int total_s = 0;
						boolean check = true;
						int cnt = 0;
						
						for(int Fb_id : Map[i][j]) {
							cnt++;
							total_m += Fireballs.get(Fb_id).m;
							total_s += Fireballs.get(Fb_id).s;
							if(Fireballs.get(Fb_id).d % 2 != direction_stat) check = false;
							Fireballs.set(Fb_id, null);
						}
						if(total_m < 5) {
							Map[i][j] = new ArrayList<>();
							continue;
						}
						
						// Fireballs에서 지우기
						// Map 업데이트 하기
						int new_id = Fireballs.size();
						Map[i][j] = new ArrayList<>();
						if(check) {	
							Map[i][j].add(new_id);
							Fireballs.add(new Fireball(new_id++, i, j, total_m/5, total_s/cnt, 0));
							Map[i][j].add(new_id);
							Fireballs.add(new Fireball(new_id++, i, j, total_m/5, total_s/cnt, 2));
							Map[i][j].add(new_id);
							Fireballs.add(new Fireball(new_id++, i, j, total_m/5, total_s/cnt, 4));
							Map[i][j].add(new_id);
							Fireballs.add(new Fireball(new_id++, i, j, total_m/5, total_s/cnt, 6));
						}
						else {
							Map[i][j].add(new_id);
							Fireballs.add(new Fireball(new_id++, i, j, total_m/5, total_s/cnt, 1));
							Map[i][j].add(new_id);
							Fireballs.add(new Fireball(new_id++, i, j, total_m/5, total_s/cnt, 3));
							Map[i][j].add(new_id);
							Fireballs.add(new Fireball(new_id++, i, j, total_m/5, total_s/cnt, 5));
							Map[i][j].add(new_id);
							Fireballs.add(new Fireball(new_id++, i, j, total_m/5, total_s/cnt, 7));
						}
					}
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.println(Map);
			}
		}
		
	}
	
	static class Fireball{
		private int id;
		private int r; // r행
		private int c; // c열
		private int m; // 질량
		private int s; // 속력
		private int d; // 방향
		
		Fireball(int id, int r, int c, int m, int s, int d){
			this.id = id;
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
		void move() {
			switch(d) {
			case 0:
				r -= s;
				r = revised_num(r);
			case 1:
				r -= s;
				c += s;
				r = revised_num(r);
				c = revised_num(c);
			case 2:
				c += s;
				c = revised_num(c);
			case 3:
				r += s;
				c += s;
				r = revised_num(r);
				c = revised_num(c);
			case 4:
				r += s;
				r = revised_num(r);
			case 5:
				r += s;
				c -= s;
				r = revised_num(r);
				c = revised_num(c);
			case 6:
				c -= s;
				c = revised_num(c);
			case 7:
				r -= s;
				c -= c;
				r = revised_num(r);
				c = revised_num(c);
			}
		}
	}
	
	static int revised_num(int num) { // 음수일 때 예외처리
		if(num <= 0) return N - (-num) % N;
		return num % N == 0 ? 7 : num % N;
	}

}