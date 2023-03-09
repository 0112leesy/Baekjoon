import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] number;
	static int[] sequence;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		number = new int[N+1];
		for(int i=1; i<=N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(number);
		
		sequence = new int[M+1];
		sb = new StringBuilder();
		
		getSequence(number[1], 1);
		
		System.out.println(sb.toString());
	}
	
	static void getSequence(int num, int depth) {
		if(depth == M+1) {
			for(int i=1; i<sequence.length; i++) {
				sb.append(sequence[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(number[i] >= sequence[depth-1]) {
				sequence[depth] = number[i];
				getSequence(number[i], depth+1);
				sequence[depth] = -1;
			}
		}
	}
}