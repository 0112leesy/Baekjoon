import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W;
	static int[] heights;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		heights = new int[W];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		int totalRain = 0;
		for(int i=0; i<W; i++) {
			totalRain += getRain(i);
		}
		System.out.println(totalRain);
	}
	
	static int getRain(int x) {
		// 왼쪽 max
		int leftMax = 0;
		for(int i=x-1; i>=0; i--) {
			leftMax = Math.max(leftMax, heights[i]);
		}
		
		// 오른쪽 max
		int rightMax = 0;
		for(int j=x+1; j<W; j++) {
			rightMax = Math.max(rightMax, heights[j]);
		}
		
		if(leftMax == 0 || rightMax == 0) return 0;
		return Math.max(Math.min(leftMax, rightMax) - heights[x], 0);
		
	}
}