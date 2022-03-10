import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		while(K-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			if(n == 0) stack.pop();
			else stack.add(n);
		}
		
		int ans = 0;
		while(!stack.isEmpty()) {
			ans += stack.pop();
		}
		System.out.println(ans);
	}

}
