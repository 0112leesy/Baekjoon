import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Stack<Integer> origin = new Stack<>();
		Stack<Integer> temp = new Stack<>();
		
		for(int i=0; i<n; i++) origin.push(n-i);
		temp.push(0);
		
		StringBuilder sb = new StringBuilder();
		while(n-- > 0) {
			int num = Integer.parseInt(br.readLine());
			
			if(num > temp.peek()){
				while(origin.peek() != num) {
					temp.push(origin.pop());
					sb.append("+\n");
				}
				temp.push(origin.pop());
				sb.append("+\n");
			}
			
			if(num == temp.peek()) {
				temp.pop();
				sb.append("-\n");
			}
			else {
				sb.delete(0, sb.length());
				sb.append("NO\n");
				break;
			}
		}
		System.out.println(sb);
	}

}
