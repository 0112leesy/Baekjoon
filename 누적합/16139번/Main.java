import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int[][] cum = new int[s.length()][26];
		
		cum[0][s.charAt(0) - 'a']++;
		for(int i=1; i<s.length(); i++) {
			int tmp = s.charAt(i) - 'a';
			for(int j=0; j<26; j++) {
				cum[i][j] = cum[i-1][j];
			}
			cum[i][tmp]++;
		}
		
		int q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(q-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			if(l == 0) {
				sb.append(cum[r][a - 'a']).append('\n');
			}
			else {
				sb.append(cum[r][a - 'a'] - cum[l-1][a - 'a']).append('\n');
			}
		}
		System.out.println(sb);
	}

}
