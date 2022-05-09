import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 시간초과
	static int[] dp;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			PriorityQueue<Integer> pq_min = new PriorityQueue<>();
			PriorityQueue<Integer> pq_max = new PriorityQueue<>(Comparator.reverseOrder());
			int K = Integer.parseInt(br.readLine());
			for(int i=0; i<K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char com = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				
				if(com == 'I') {
					pq_max.offer(num);
					pq_min.offer(num);
				}
				else if(com == 'D') {
					if(pq_max.isEmpty() || pq_min.isEmpty()) {
						pq_max.clear();
						pq_min.clear();
					}
					else {
						if(num == 1) {
							int target = pq_max.poll();
							pq_min.remove(target);
						}
						else if(num == -1) {
							int target = pq_min.poll();
							pq_max.remove(target);
						}
					}
				}
			}
			if(pq_max.isEmpty() || pq_min.isEmpty()) sb.append("EMPTY\n");
			else sb.append(pq_max.peek()+" "+pq_min.peek()+"\n");
		}
		System.out.println(sb);
		
	}
	

}
