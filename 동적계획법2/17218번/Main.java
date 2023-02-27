import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static char[] str1;
	static char[] str2;
	// static int[][] LCS;
	static String[][] LCS;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String tmp1 = br.readLine();
		String tmp2 = br.readLine();
		str1 = new char[tmp1.length()+1];
		str2 = new char[tmp2.length()+1];
			
		for(int i=0; i<tmp1.length(); i++) {
			str1[i+1] = tmp1.charAt(i);
		}
		for(int i=0; i<tmp2.length(); i++) {
			str2[i+1] = tmp2.charAt(i);
		}
		
		// LCS = new int[str1.length][str2.length];
		LCS = new String[str1.length][str2.length];
		// Arrays.fill(LCS[0], 0);
		Arrays.fill(LCS[0], "");
		for(int i=1; i<str1.length; i++) {
			// LCS[i][0] = 0;
			LCS[i][0]= "";
		}
		
		for(int i=1; i<str1.length; i++) {
			for(int j=1; j<str2.length; j++) {
				if(str1[i] == str2[j]) {
					LCS[i][j] = LCS[i-1][j-1] + str1[i];
					// LCS[i][j] = LCS[i-1][j-1] + 1;
				} else {
					if(LCS[i-1][j].length() > LCS[i][j-1].length()) {
						LCS[i][j] = LCS[i-1][j];
					} else {
						LCS[i][j] = LCS[i][j-1];
					}
					// LCS[i][j] = Math.max(LCS[i][j-1], LCS[i-1][j]);
				}
			}
		}
		
		System.out.println(LCS[tmp1.length()][tmp2.length()]);
	}
	
	// 점화식
	// if(str1[i] == str2[j]) LCS(i, j) = LCS(i-1, j-1) + 1
	// else LCS(i, j) = Math.max(LCS(i, j-1), LCS(i-1, j))
	
	
	/*
	 * LCS(Longest Common Subsequence): 최장 공통 부분수열
	 * ACAYKP와 CAPCAK의 LCS를 구하려면 ACAYKP를 기준으로 두고 CAPCAK를 비교한다
	 * N = CAPCAK의 길이 6
	 * M = ACAYKP의 길이 6
	 * 
	 * - i행과 j열이 같은 문자면 (i-1,j-1) + 1 즉, 왼쪽 위의 값 + 1
	 * - i행과 j열이 다른 문자면 (i-1,j)와 (i,j-1) 중 큰 값 즉, Max(왼쪽 값, 위쪽 값)
	 * 
	 * 		0	A	C	A	Y	K	P	(j) ~ 최대 M(str2)
	 * 0	0	0	0	0	0	0	0
	 * C	0	0	1	1	1	1	1
	 * A	0	1	1	2	2	2	2
	 * P	0	1	1	2	2	2	3
	 * C	0	1	2	2	2	2	3
	 * A	0	1	2	3	3	3	3
	 * K	0	1	2	3	3	4	4
	 * 
	 * (i) ~ 최대 N(str1)
	 * 
	 * 부분 수열의 길이 arr(N, M)는 다음과 같이 구할 수 있고
	 * 부분 수열 자체는 다음과 같이 구한다
	 * 
	 * 1. 우선 가장 큰 숫자가 시작된 곳을 체크함
	 * 2. 이후 이전 숫자를 찾아 가며 체크를 하되, 각각의 행과 열에는 하나의 체크만이 되어야 함	
	 * 
	 */
		
}