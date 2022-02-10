import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
 
 
public class Main {	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> sm_pq = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> lar_pq = new PriorityQueue<>();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(sm_pq.size() == lar_pq.size()) {
				sm_pq.offer(x);
				
				if(!lar_pq.isEmpty() && sm_pq.peek() > lar_pq.peek()) {
					sm_pq.offer(lar_pq.poll());
					lar_pq.offer(sm_pq.poll());
				}
			}
			
			else {
				lar_pq.offer(x);
				
				if(sm_pq.peek() > lar_pq.peek()) {
					sm_pq.offer(lar_pq.poll());
					lar_pq.offer(sm_pq.poll());
				}
			}
			
			sb.append(sm_pq.peek()).append('\n');
			
		}
		System.out.println(sb);
		
	}	
	
 
}