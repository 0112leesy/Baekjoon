import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String temp = "";
		long ans = 0;
		boolean flag = false;
		
		// StringTokenizer 나 split 함수를 통해 '-'를 기준으로 나누고,
		// 나뉜 문자열은 '+' 기준으로 나누어 모두 더한 뒤 빼주는 방법으로도 풀이 가능
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c == '+') {
				if(flag) {
					ans -= Integer.parseInt(temp);
				}
				else {
					ans += Integer.parseInt(temp);
				}
				temp = "";
				
			}
			else if(c == '-') {
				if(flag) {
					ans -= Integer.parseInt(temp);
				}
				else {
					ans += Integer.parseInt(temp);
				}
				flag = true;
				temp = "";
			}
			else {
				temp += Character.toString(c);
			}
		}
		if(flag == true) ans -= Integer.parseInt(temp);
		else ans += Integer.parseInt(temp);
		
		System.out.println(ans);
	}
	
}