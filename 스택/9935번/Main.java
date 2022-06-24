import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main { // 문자열의 최대 길이가 100만이기 때문에 문자열 메소드 사용 주의
	
	static String bomb;
	static Stack<Character> stack;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		bomb = br.readLine();
		
		stack = new Stack<>();
		for(int i=0; i<input.length; i++) {
			stack.add(input[i]); // 스택에 문자 추가
			
			if(hasBomb()) { // 폭발 문자열이 있다면 pop 연산
				for(int j=0; j<bomb.length(); j++) stack.pop();
			}
		}
		
		if(stack.isEmpty()) System.out.println("FRULA");
		else {
			StringBuilder sb = new StringBuilder();
			for(char c : stack) sb.append(c);
			System.out.println(sb);
		}
		
	}
	
	static boolean hasBomb() { // stack 끝에 폭발문자열이 있는지 확인
		if(stack.size() < bomb.length()) return false;
		
		for(int i=0; i<bomb.length(); i++) { // stack의 get 연산 사용
			if(stack.get(stack.size()-bomb.length()+i) != bomb.charAt(i)) return false;
		}

		return true;
	}

}
