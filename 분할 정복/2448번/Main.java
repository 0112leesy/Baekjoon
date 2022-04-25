import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static String[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		arr = new String[N];
		arr[0] = "  *  ";
		arr[1] = " * * ";
		arr[2] = "*****";
		
		// bottom-up
		for(int i=1; 3*(int)Math.pow(2, i)<=N; i++) {
			// i = 1, 2, 3 ... -> level
			get_star(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(arr[i]).append('\n');
		}
		System.out.println(sb);

	}
	
	static void get_star(int level) {
		int bottom = 3 * (int)Math.pow(2, level);
		// bottom = 6, 12, 24, 48 ...
		int middle = bottom/2;
		// middle = 3, 6, 12, 24 ...
		
		for(int i=middle; i<bottom; i++) { // 기존 행들 아래에 새로 생긴 행들을 채움
			// 가운데 공백 하나를 두고 위에 있는 그대로 양 옆 차례로 채움
			arr[i] = arr[i-middle] + " " + arr[i-middle];
		}
		
		String space = " "; // 기존 행들 양 옆에 채울 외부 공백
		while(space.length()<middle) {
			space += " "; // middle만큼의 공백 필요
		}
		for(int i=0; i<middle; i++) {
			arr[i] = space + arr[i] + space;
		}
	}
		
}

