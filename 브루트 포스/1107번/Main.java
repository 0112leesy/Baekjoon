import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static boolean[] isFine;
	static int[] number;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String stringN = br.readLine();
		int len = stringN.length();
		
		number = new int[len];
		for(int i=0; i<len; i++) {
			number[i] = stringN.charAt(i) - '0';
		}
		
		N = Integer.parseInt(stringN);
		int M = Integer.parseInt(br.readLine());
		
		isFine = new boolean[10];
		Arrays.fill(isFine, true);
		
		if(M != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				int unable = Integer.parseInt(st.nextToken());
				isFine[unable] = false;
			}
		}
		
		int case1 = getCase1(N);
//		if(M == 10) {
//			System.out.println(case1);
//			return;
//		}

		int plus = searchPlus();
		int minus = searchMinus();
		int case2 = Math.min(plus, minus);		
		int answer = Math.min(case1, case2);
		System.out.println(answer);
		
	}
	
	static int getCase1(int N) {
		return Math.abs(N - 100);
	}
	
	static boolean isAble(int n) {
		char[] arr = Integer.toString(n).toCharArray();
		for(int i=0; i<arr.length; i++) {
			if(!isFine[arr[i] - '0']) return false;
		}
		return true;
	}
	
	static int searchPlus() {
		
		boolean flag = false;
		for(int i=1; i<=9; i++) {
			if(isFine[i]) {
				flag = true;
				break;
			}
		}
		
		if(!flag) return Integer.MAX_VALUE;
		
		int n = N;
		int cnt = 0;
		while(!isAble(n)) {
			cnt += 1;
			n += 1;
		}
		int len = Integer.toString(n).length();
		return cnt + len;
	}
	
	static int searchMinus() {
		int n = N;
		int cnt = 0;
		while(!isAble(n)) {
			cnt += 1;
			n -= 1;
			if(n < 0) return Integer.MAX_VALUE;
		}
		int len = Integer.toString(n).length();
		return cnt + len;
	}

}