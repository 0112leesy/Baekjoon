import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int studentNumber;
	static int[] studentChoice;
	static int[] visited;
	static boolean[] finished;
	static int visit_order;
	static int soloCount;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			studentNumber = Integer.parseInt(br.readLine());
			soloCount = studentNumber;
			studentChoice = new int[studentNumber+1];
			visited = new int[studentNumber+1];
			finished = new boolean[studentNumber+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=studentNumber; i++) {
				visited[i] = -1;
				studentChoice[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1; i<=studentNumber; i++) {
				int choice = studentChoice[i];
				if(visited[choice] != -1) { // 이미 방문한 학생의 경우 continue
					continue;
				}
				DFS(choice);
			}
			sb.append(soloCount + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void DFS(int student) {
		visited[student] = visit_order++;
		
		int nextStudent = studentChoice[student];
		if(visited[nextStudent] == -1) { // 아직 방문하지 않은 경우 DFS 진행
			DFS(nextStudent);
		}
		else { // 방문한 학생이지만 finished 상태가 아닌 경우 사이클 크기만큼 감소시켜줌
			if(finished[nextStudent] != true) {
				// 이때 사이클 크기는 (현재 order - 이미 방문된 학생의 visited order 값)
				soloCount -= (visit_order - visited[nextStudent]);
			}
			
		}
		finished[student] = true; // DFS 함수의 수행이 끝나면 finished 즉 해당 학생은 볼일 끝남
	}
	
}