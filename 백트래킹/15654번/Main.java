import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] ans, arr;
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		//for(int i=0; i<N; i++)System.out.println(arr[i]);
		
		ans = new int[M];
		sb = new StringBuilder();
		
		Permutation(0);
		System.out.println(sb);
	}
	
	static void Permutation(int n) {
		if(n == M) {
			StringBuilder tmp = new StringBuilder();
			for(int i=0; i<M; i++) tmp.append(ans[i]+" ");
			sb.append(tmp).append('\n');
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(check(n, arr[i])) ans[n] = arr[i];
			Permutation(n+1);
		}
	}
	
	static boolean check(int until, int x) {
		for(int i=0; i<until; i++) {
			if(x == ans[i]) return false;
		}
		return true;
	}
}
