import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
 
 
public class Main {	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Deque<Integer> deque = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			switch(cmd) {
			
			case "push_front":
				int x = Integer.parseInt(st.nextToken());
				deque.offerFirst(x);
				break;
				
			case "push_back":
				int y = Integer.parseInt(st.nextToken());
				deque.offerLast(y);
				break;
				
			case "pop_front":
				if(deque.isEmpty()) sb.append("-1\n");
				else sb.append(deque.pollFirst()).append('\n');
				break;
				
			case "pop_back":
				if(deque.isEmpty()) sb.append("-1\n");
				else sb.append(deque.pollLast()).append('\n');
				break;
				
			case "size":
				sb.append(deque.size()).append('\n');
				break;
				
			case "empty":
				if(deque.isEmpty()) sb.append("1\n");
				else sb.append("0\n");
				break;
				
			case "front":
				if(deque.isEmpty()) sb.append("-1\n");
				else sb.append(deque.peekFirst()).append('\n');
				break;
				
			case "back":
				if(deque.isEmpty()) sb.append("-1\n");
				else sb.append(deque.peekLast()).append('\n');
				break;
				
			}
			
		}
		
		System.out.println(sb);
		
	}	
	
 
}