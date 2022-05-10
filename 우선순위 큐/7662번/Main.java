import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			HashMap<Integer, Integer> hmap = new HashMap<>();
			PriorityQueue<Integer> min_queue = new PriorityQueue<>();
			PriorityQueue<Integer> max_queue = new PriorityQueue<>(Collections.reverseOrder());
			int K = Integer.parseInt(br.readLine());
			for(int i=0; i<K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int com = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				
				if(com == 'I') {
					hmap.put(num, hmap.getOrDefault(num, 0)+1);
					
					min_queue.offer(num);
					max_queue.offer(num);
				}
				else if(com == 'D') {
					if(hmap.size()==0) continue;
					else {
						if(num == -1) {
							D(hmap, min_queue);
						}
						if(num == 1) {
							D(hmap, max_queue);
						}
					}
				}
			}
			if(hmap.size() == 0) sb.append("EMPTY\n");
			else{
				int max_num = D(hmap, max_queue);
				sb.append(max_num+" ");
				if(hmap.size()==0) sb.append(max_num+'\n');
				else sb.append(D(hmap, min_queue)+'\n');
			}
			
		}
		System.out.println(sb);
		
	}
	
	static int D(HashMap<Integer, Integer> map, PriorityQueue<Integer> pq) {
		int target = 0;
		while(true) {
			target = pq.poll();
			int cnt = map.getOrDefault(target, 0);
			if(cnt == 0) continue;
			if(cnt == 1) map.remove(target);
			else map.put(target, cnt-1);
			break;
		}
		return target;
	}
	

}
