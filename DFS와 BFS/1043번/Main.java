import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 진실을 아는 사람들과 연결된 사람들은 진실을 들어야 함

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람의 수
		int M = Integer.parseInt(st.nextToken()); // 파티의 수
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 연결그래프
		for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
		
		Queue<Integer> queue = new LinkedList<>(); // BFS를 위한 큐
		boolean[] pos = new boolean[N+1]; // pos가 true인 경우 진실을 말해야 함
		
		st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
		if(P > 0) { // 진실을 아는 사람이 있다면
			for(int i=0; i<P; i++) {
				int p = Integer.parseInt(st.nextToken());
				queue.offer(p); // 진실을 아는 사람들 부터 BFS 탐색
				pos[p] = true;
			}
		}
		else { // 진실을 아는 사람이 0명이면 파티의 수만큼 과장된 이야기 가능
			System.out.println(M);
			return;
		}
		
		ArrayList<ArrayList<Integer>> arr = new ArrayList<>(); // 파티 정보
		for(int i=0; i<M; i++) arr.add(new ArrayList<>());
		
 		for(int i=0; i<M; i++) {
 			st = new StringTokenizer(br.readLine());
 			int size = Integer.parseInt(st.nextToken());
 			for(int j=0; j<size; j++) {
 				int n = Integer.parseInt(st.nextToken());
 				arr.get(i).add(n); // 각 파티 별로 참여 인원 기록
 			}
 		}
 		
 		for(ArrayList<Integer> party : arr) { // 각 파티별로 살펴봄
 			for(int i=0; i<party.size(); i++) {
 				Integer p = party.get(i); // 각 파티의 참여 인원들 별로
 				for(int j=0; j<party.size(); j++) { // 같은 파티 내의 인원들을 연결 그래프에 추가
 					if(p != party.get(j)) graph.get(p).add(party.get(j));
 				}
 			}
 		}
		
		while(!queue.isEmpty()) { // BFS 탐색
			int temp = queue.poll();
			
			for(int connected : graph.get(temp)) { // 연결 그래프 내의 사람들 중
				if(!pos[connected]) { // pos가 false인 사람들의 경우
					pos[connected] = true; // pos를 true로 지정하고
					queue.offer(connected); // 큐에 집어넣어서 탐색 진행
				}
			}
		}
		
		int cnt = 0;
		for(ArrayList<Integer> party : arr) { // 각 파티별로
			boolean check = false;
			for(int members : party) { // 멤버들이 모두 pos가 false이면 과장된 이야기 가능
				if(pos[members]) {
					check = true;
					break;
				}
			}
			if(!check) cnt++;
		}
		System.out.println(cnt);
		
		
	}

}
