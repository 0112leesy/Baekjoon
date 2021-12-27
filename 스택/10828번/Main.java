import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			if(str[0].equals("push")) {
				stack.push(Integer.parseInt(str[1]));
			}
			else if(str[0].equals("pop")) {
				if(stack.empty()) sb.append(-1).append('\n');
				else sb.append(stack.pop()).append('\n');
			}
			else if(str[0].equals("size")) {
				sb.append(stack.size()).append('\n');
			}
			else if(str[0].equals("empty")) {
				if(stack.empty()) sb.append(1).append('\n');
				else sb.append(0).append('\n');
			}
			else if(str[0].equals("top")) {
				if(stack.empty()) sb.append(-1).append('\n');
				else sb.append(stack.peek()).append('\n');
			}
		}
		System.out.println(sb);
	}

}
