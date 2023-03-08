import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[][] len;
	static String str1;
	static String str2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine();
		str2 = br.readLine();
		
		len = new int[str1.length()+1][str2.length()+1];
		
		String LCS = getLCS();
		System.out.println(LCS.length() + "\n" + LCS);
		
	}
	
	static String getLCS() {
		for(int i=0; i<str1.length(); i++) {
			for(int j=0; j<str2.length(); j++) {
				if(str1.charAt(i) == str2.charAt(j)) {
					len[i+1][j+1] = len[i][j] + 1;
				}
				else {
					if(len[i][j+1] < len[i+1][j]) {
						len[i+1][j+1] = len[i+1][j];
					}
					else {
						len[i+1][j+1] = len[i][j+1];
					}
				}
			}
		}
		
		int x = str1.length();
		int y = str2.length();
		String answer = "";
		
		while(x > 0 && y > 0) {
			if(x == 0 || y == 0) break;
			
			if(len[x-1][y] == len[x][y]) {
				x -= 1;
			}
			else if(len[x][y-1] == len[x][y]) {
				y -= 1;
			}
			else {
				answer = str1.charAt(x-1) + answer;
				x -= 1;
				y -= 1;
			}
		}
		
		return answer;
	}
}