import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    // 연산자와 괄호 우선순위를 지정하고 스택에 넣어서 피연산자와 함께 후위 표기식을 만들어나가는 것
    // 인데 엄청난 빡구현으로 풀었다 :/
    // 우선 순위에 따라 괄호 모두 치기 -> 후위 표기식으로 변환
    // 괄호 모두 치는 알고리즘에 재귀까지 도입했다
    // 또 주먹구구식으로 풀어버린 것 같지만 끈질지게 디버깅해서 맞췄다 !

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		String put = putAllBracket(str);
		System.out.println(put);
		
		String postfix = returnToPostfix(put);
		System.out.println(postfix);
	}
	
	static String putAllBracket(String str) {
		Deque<String> dequeNum = new LinkedList<>();
		Deque<Character> dequeOpr = new LinkedList<>();
		
		String temp = "";
		
		Stack<Character> brac = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c == '+' || c == '-' || c == '*' || c == '/') {
				if(!brac.isEmpty()) {
					temp += Character.toString(c);
				}
				else {
					dequeOpr.add(c);
				}
			}
			else if(c == '(') {
				brac.add(c);
				if(brac.size() > 1) {
					temp += Character.toString(c);
				}
			}
			else if(c == ')') {
				if(brac.size() > 1) {
					temp += Character.toString(c);
				}
				brac.pop();
				if(brac.isEmpty()) {
					// System.out.println("temp : " + temp);
					String pab = putAllBracket(temp);
					// System.out.println("pab : "+pab);
					dequeNum.addLast(pab);
					if(dequeOpr.peekLast() != null) {
						if(dequeOpr.peekLast() == '*' || dequeOpr.peekLast() == '/') {
							String num2 = dequeNum.pollLast();
							String num1 = dequeNum.pollLast();
							char opr = dequeOpr.pollLast();
							String newNum = "(" + num1 + opr + num2 + ")";
							dequeNum.add(newNum);
						}
					}
					temp = "";
				}
			}
			else {
				if(!brac.isEmpty()) {
					temp += Character.toString(c);
				}
				else {
					dequeNum.add(Character.toString(c));
					if(dequeOpr.peekLast() != null) {
						if(dequeOpr.peekLast() == '*' || dequeOpr.peekLast() == '/') {
							String num2 = dequeNum.pollLast();
							String num1 = dequeNum.pollLast();
							char opr = dequeOpr.pollLast();
							String newNum = "(" + num1 + opr + num2 + ")";
							dequeNum.add(newNum);
						}
					}
					else {
						//System.out.println(dequeOpr.isEmpty() + " / " + dequeNum.peek());
					}
				}
			}
		}
		
		while(!dequeOpr.isEmpty()) {
			String num1 = dequeNum.pollFirst();
			String num2 = dequeNum.pollFirst();
			char opr = dequeOpr.pollFirst();
			
			String newNum = "(" + num1 + opr + num2 + ")";
			dequeNum.addFirst(newNum);
		}
 
		return dequeNum.pollFirst();
	}
	
	static String returnToPostfix(String str) {
		Stack<Character> bracket = new Stack<>();
		Stack<String> number = new Stack<>();
		Stack<Character> operator = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c == '(') { // 여는 괄호
				bracket.add(c);
			}
			else if(c == ')') { // 닫는 괄호
				bracket.pop();
				String num2 = number.pop();
				String num1 = number.pop();
				String opr = Character.toString(operator.pop());
				
				String newNum = num1 + num2 + opr;
				// System.out.println(newNum);
				number.add(newNum);
			}
			else if(c == '+' || c == '-' || c == '*' || c == '/') { // 연산자
				operator.add(c);
			}
			else { // 피연산자
				number.add(Character.toString(c));
			}
		}
		
		return number.pop();
	}
}