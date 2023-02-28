import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String S = br.readLine();
		
		int IOICount = 0; // 연속되는 IOI를 count 하는 수
		int answer = 0;
		for(int i=1; i<M-1; i++) {
			if(S.charAt(i-1) == 'I' && S.charAt(i) == 'O' && S.charAt(i+1) == 'I') {
				IOICount += 1;
				if(IOICount == N) {
					IOICount -= 1;
					answer += 1;
				}
				i += 1;
			} else {
				IOICount = 0;
			}
		}
		System.out.println(answer);
	}
}