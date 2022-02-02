import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
 
 
public class Main {	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			LinkedList<int[]> queue = new LinkedList<>(); // get()함수 사용을 위해 LinkedList로 선언
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				// { 초기 문서의 위치, 문서의 중요도 } 큐에 저장
				int[] temp = {j, Integer.parseInt(st.nextToken())};
				queue.offer(temp);
			}
			
			int cnt = 0; // 문서 출력 횟수
			while(!queue.isEmpty()) {
				int[] front = queue.poll(); // 맨 앞의 문서를 우선 뽑는다
				boolean isMax = true; // front의 중요도가 가장 높은 중요도인지 체크 (일단 true)
				
				for(int j=0; j<queue.size(); j++) { // 문서의 중요도 순차적으로 탐색
					if(front[1] < queue.get(j)[1]) { // front보다 높은 중요도의 문서가 나타났을 때
						queue.offer(front); // front를 맨 뒤로 보낸다
						for(int k=0; k<j; k++) { // 그 후, 해당 문서 직전까지의 문서들을 맨 뒤로 차례대로 옮긴다 
							queue.offer(queue.poll());
						}
						
						isMax = false; // front 보다 높은 중요도의 문서가 나타났으므로 false 대입
						break; // 탐색 종료
					}
				}
				
				if(isMax == false) continue; // front의 중요도가 가장 높지 않으므로 문서를 출력하지 않고 다시 루프를 돈다
				
				cnt++; // front의 중요도가 가장 높을땐 문서를 출력한다 (front가 뽑힌 뒤 다시 큐에 추가되지 않음)
				if(front[0] == M) break; // 문서를 출력했을 때 해당 문서의 초기 위치가 찾는 위치(M)이라면 탐색 종료
				
			}
			sb.append(cnt).append('\n');
			
		}
		System.out.println(sb);
	}	
	
 
}