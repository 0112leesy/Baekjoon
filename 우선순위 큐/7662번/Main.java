import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main { // HashMap 대신 TreeMap 사용 가능
	
	static Map<Integer, Integer> hmap;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			hmap = new HashMap<>();
			PriorityQueue<Integer> min_queue = new PriorityQueue<>();
			PriorityQueue<Integer> max_queue = new PriorityQueue<>(Collections.reverseOrder());
			int K = Integer.parseInt(br.readLine());
			for(int i=0; i<K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String com = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				if(com.equals("I")) {
					hmap.put(num, hmap.getOrDefault(num, 0)+1);
					
					min_queue.add(num);
					max_queue.add(num);
				}
				else{
					if(hmap.size()==0) continue;
					if(num == 1) delete(max_queue);
					else delete(min_queue);
				}
			}
			if(hmap.size() == 0) sb.append("EMPTY\n");
			else{
				int res = delete(max_queue);
				sb.append(res+" ");
				if(hmap.size()>0) res = delete(min_queue);
				sb.append(res+"\n");
			}
			
		}
		System.out.println(sb.toString());
		
	}
	
	static int delete(PriorityQueue<Integer> pq) {
		int target = 0;
		while(true) {
			target = pq.poll();
			int cnt = hmap.getOrDefault(target, 0);
			if(cnt == 0) continue;
			if(cnt == 1) hmap.remove(target);
			else hmap.put(target, cnt-1);
			break;
		}
		return target;
	}

}
