import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, r, c;
	static int size;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		size = (int)Math.pow(2, N);
		
		System.out.println(Z(size, 0, 0));
	}
	
	// size가 1일때까지 (r,c)가 해당된 사분면을 재귀적으로 방문함
	static int Z(int size, int x, int y) {
		if(size == 1) return 0;
		if(r < x+size/2 && c < y+size/2)
			return Z(size/2, x, y);
		else if(r < x+size/2 && c < y+size)
			return Z(size/2, x, y+size/2) + (int)Math.pow(size/2, 2);
		else if(r < x+size && c < y+size/2)
			return Z(size/2, x+size/2, y) + (int)Math.pow(size/2, 2)*2;
		else
			return Z(size/2, x+size/2, y+size/2) + (int)Math.pow(size/2, 2)*3;
	}
	

}
