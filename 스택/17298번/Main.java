import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<Integer> input = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) input.push(Integer.parseInt(st.nextToken()));
		
		Stack<Integer> temp = new Stack<>();
		temp.push(input.peek());
		
		Stack<Integer> ans = new Stack<>();
		
		while(!input.empty()) {
			int num = input.pop();
					
			while(num >= temp.peek()) {
				temp.pop();
				if(temp.empty()) {
					ans.push(-1);
					temp.push(num);
					break;
				}
			}
			
			if(num < temp.peek()) {
				ans.push(temp.peek());
				temp.push(num);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!ans.empty()) sb.append(ans.pop()).append(' ');
		System.out.println();
		
	}
	/* 
	 * num < top : top이 오큰수, num 푸시
	 * num >= top : num < top이 될때까지 pop, top이 오큰수, num 푸시
	 * 									-> 이때 temp가 비면 오큰수는 -1, num 푸시
	 * 
	 */

}
