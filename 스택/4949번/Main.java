import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	static Stack<Character> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			String str = br.readLine();
			if(str.equals(".")) break;
			
			sb.append(check(str)? "yes" : "no").append('\n');
		}		
		
		System.out.println(sb);
	}
	
	static boolean check(String str) {
		stack = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c == '(' || c == '[') stack.add(c);
			else if(c == ')') {
				if(stack.isEmpty() || stack.peek() != '(') return false;
				else stack.pop();
			}
			else if(c == ']') {
				if(stack.isEmpty() || stack.peek() != '[') return false;
				else stack.pop();
			}
		}
		if(stack.isEmpty()) return true;
		else return false;
	}

}
