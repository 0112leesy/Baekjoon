import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[2*N];
		boolean[] bot = new boolean[2*N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<2*N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int K_count = 0;
		int ans_count = 0;
		
		/* 0. ans_count ++;
		 * 1. 로봇 유무 배열과 내구도 배열 한칸 회전 진행
		 * 2. 내리는 위치부터 올리는 위치까지 탐색. 이동 가능한 로봇은 이동시킨다 (K_count 변화)
		 * 3. 올리는 위치의 내구도 배열값 확인, 가능하면 로봇을 올림 (K_count 변화)
		 * 4. K_count 값 확인
		 */
		
		while(K_count < K) {
			// 단계 up
			ans_count++;
			// 내구도 배열, 로봇 유무 배열 회전 진행
			int arr_tmp = arr[2*N-1];
			boolean bot_tmp = bot[2*N-1];
			for(int i=2*N-1; i>0; i--) {
				arr[i] = arr[i-1];
				bot[i] = bot[i-1];
			}
			arr[0] = arr_tmp;
			bot[0] = bot_tmp;
			// 내리는 위치에 로봇이 있으면 내려줌
			if(bot[N-1]) bot[N-1] = false;
			// 내리는 위치부터 올리는 위치까지 탐색, 이동 가능한 로봇은 이동시킴 (K_count 변화)
			for(int i=N-1; i>0; i--) {
				if(bot[i]) { // 먼저 올려진 로봇부터(=벨트 끝쪽에 있는 로봇부터) 탐색, 로봇이 있으면
					if(!bot[i+1] && arr[i+1]>0) { // 다음칸에 로봇 없고 내구도 1 이상이면 이동
						// 로봇 이동
						bot[i+1] = true;
						bot[i] = false;
						arr[i+1] -= 1;
						// 이동된 위치 내구도가 0이 되면 K_count 증가
						if(arr[i+1] == 0) K_count++;
						// 이동된 위치가 내리는 곳이면 로봇을 내려줌
						if(i == N-2) bot[i+1] = false;
					}
					
				}
			}
			// 올리는 위치 내구도가 0이 아니면 해당 위치에 로봇을 올림
			if(arr[0] != 0) {
				arr[0]--;
				bot[0] = true;
				if(arr[0] == 0) K_count++; // 로봇을 올렸으니까 내구도 감소
			}
			
		}
		System.out.println(ans_count);
	}
	
}
