import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
        // 에라토스테네스의 체 알고리즘으로 소수 판정
		boolean[] nums = new boolean[4000000];
		for(int i=2; i<4000000; i++) {
			for(int j=2; i*j<4000000; j++) {
				nums[i*j] = true;
			}
		}
		
		ArrayList<Integer> primes = new ArrayList<>();
		for(int i=2; i<4000000; i++) {
			if(!nums[i]) primes.add(i);
		}
		
		if(N == 2) {
			System.out.println(1);
			return;
		}
		
        // 투 포인터 및 누적합 알고리즘 사용
		int l=0;
		int r=1;
		long sum = primes.get(l) + primes.get(r);
		int cnt = 0;
		
		while(l < primes.size() && r < primes.size()) {
			if(sum == N) { // 경우의 수 추가
				cnt++;
				sum -= primes.get(l++); // 값을 줄여보기
			}
			else if(sum > N) { // 값이 작아져야 함
				sum -= primes.get(l++);
			}
			else if(sum < N) { // 값이 커져야 함
				if(r == primes.size()-1) break;
				sum += primes.get(++r);
			}
		}
		
		System.out.println(cnt);
	}
}
