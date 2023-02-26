import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static char[] gear1;
	static char[] gear2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
	
		// 항상 gear1이 gear2보다 짧거나 같음
		if(str1.length() > str2.length()) {
			String temp = "";
			temp = str1;
			str1 = str2;
			str2 = temp;
		}
		
		gear1 = str1.toCharArray();
		gear2 = str2.toCharArray();
		
		System.out.println(getOptimalWidth());
	}
	
	static int getOptimalWidth() {
		int forwardWidth = 0;
		int backwardWidth = 0;
		
		// System.out.println("선행 탐색");
		for(int i=0; i<gear2.length; i++) {
			if(isPossibleForward(i)) {
				forwardWidth = Math.max(gear2.length, gear1.length + i);
				break;
			}
		}
		
		
		// System.out.println("역행 탐색");
		for(int i=0; i<gear1.length; i++) {
			if(isPossibleBackward(i)) {
				backwardWidth = i + gear2.length;
				break;
			}
		}
		
		
		if(forwardWidth + backwardWidth != 0) {
			// System.out.println("선행너비 : " + forwardWidth + "\n역행너비 : " + backwardWidth );
			if(forwardWidth == 0) {
				return backwardWidth;
			}
			if(backwardWidth == 0) {
				return forwardWidth;
			}	
			return Math.min(forwardWidth, backwardWidth);
		}
	
		// System.out.println("끼워 넣을 수 없음");
		return gear1.length + gear2.length;
	}
	
	static boolean isPossibleForward(int index) {
		// System.out.println("Forward Evaluate " + index);
		for(int i=0; i<gear1.length; i++) {
			if(index + i >= gear2.length) { // gear2보다 긴 부분은 pass
				break;
			}
			// System.out.print("	check " + i + " > ");
			if((gear1[i] - '0') + (gear2[index + i] - '0') > 3) {
				// System.out.println((gear1[i] - '0') + (gear2[index + i] - '0') + "is unavailable");
				return false;
			}
			// System.out.println((gear1[i] - '0') + (gear2[index + i] - '0')+"is available");
		}
		return true;
	}
	
	static boolean isPossibleBackward(int index) {
		// System.out.println("Backward Evaluate " + index);
		for(int i=index; i<gear1.length; i++) {
			if((gear1[i] - '0') + (gear2[i - index] - '0') > 3) {
				return false;
			}
		}
		return true;
	}
}