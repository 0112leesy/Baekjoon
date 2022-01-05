import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
	
	static int N;
	static int scores[];
	static Integer sum[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		scores = new int[N+1];
		sum = new Integer[N+1];
		scores[0] = 0;
		for(int i=1; i<=N; i++) {
			scores[i] = Integer.parseInt(br.readLine());
		}
		sum[0] = scores[0];
		sum[1] = scores[1];
		// 시작점은 계단으로 취급하지 않기 때문에 1번, 2번 계단을 연속해서 오를 수 있음
		if(N >= 2) sum[2] = scores[1] + scores[2];
		
		System.out.println(func(N));

	}
	
	static int func(int x) {
		// 두 번 연속해서 한 칸씩 계단을 오를 수 없으며
		// 연속해서 한 칸을 올랐는지 여부를 top-down 재귀를 통해 알 수 없으므로
		// 한 칸 계단을 올라서 해당 계단에 다다른 경우는
		// func(x-3) + scores[x-1]로 처리해줌
		if(sum[x] == null) {
			sum[x] = Math.max(func(x-2), func(x-3) + scores[x-1]) + scores[x];
		}
		
		return sum[x];
	}

	
}