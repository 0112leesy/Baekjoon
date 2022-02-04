import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
 
 
public class Main {	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//LinkedList<Integer> deque = new LinkedList<>(); -> indexOf 연산을 통해 위치 확인을 하여 풀이도 가능
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i=1; i<=N; i++) dq.offer(i);
		
		int[] target = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) target[i] = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		for(int i=0; i<M; i++) {
			
			int target_num = target[i];
			int cnt = 0;
			while(dq.peekFirst() != target_num) {
				dq.offerLast(dq.pollFirst()); // 2번 연산 반복적으로 실행
				cnt ++; // 연산 횟수 증가
			} // 맨 앞이 target_num 임
			if(cnt > dq.size()/2) cnt = dq.size()-cnt; // 반대로 한 것으로 취급
			dq.poll(); // target_num 제거
			ans += cnt;
			
		}
		System.out.println(ans);
		
		
	}	
	
 
}