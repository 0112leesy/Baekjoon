import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static String[] arr;
	static int A,B;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			arr = new String[10000];
			arr[A] = "X";
			
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(A);
			
			while(!queue.isEmpty()) {
				int x = queue.poll();
				if(x == B) {
					sb.append(arr[x].substring(1)).append('\n');
					break;
				}
				int D = (2*x)%10000;
				if(arr[D] == null) {
					arr[D] = arr[x].concat("D");
					queue.offer(D);
				}
				int S = x==0?9999:x-1;
				if(arr[S] == null) {
					arr[S] = arr[x].concat("S");
					queue.offer(S);
				}
				
				int d1 = x/1000;
				int d2 = (x%1000)/100;
				int d3 = (x%100)/10;
				int d4 = x%10;
				
				int L = d2*1000 + d3*100 + d4*10 + d1;
				if(arr[L] == null) {
					arr[L] = arr[x].concat("L");
					queue.offer(L);
				}
				int R = d4*1000 + d1*100 + d2*10 + d3;
				if(arr[R] == null) {
					arr[R] = arr[x].concat("R");
					queue.offer(R);
				}
				
			}
		}
		System.out.println(sb);
		
	}

}
