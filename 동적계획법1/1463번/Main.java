import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
	
	static int N;
	static Integer arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new Integer[N+1];
		arr[0] = 0;
		arr[1] = 0;
		System.out.println(func(N));

	}
	
	static int func(int x) {
		
		if(arr[x] == null) {
			// 각 경우의 최소연산 횟수를 구한다
			if(x % 6 == 0) {// 3으로 나누거나, 2로 나누거나, 1 빼기 가능
				arr[x] = Math.min(func(x/3), Math.min(func(x/2), func(x-1))) + 1;
			}
			else if(x % 3 == 0) {// 3으로 나누거나, 1 빼기 가능
				arr[x] = Math.min(func(x/3), func(x-1)) + 1;
			}
			else if(x % 2 == 0) {// 2로 나누거나, 1 빼기 가능
				arr[x] = Math.min(func(x/2), func(x-1)) + 1;
			}
			else {// 1 빼기만 가능
				arr[x] = func(x-1) + 1;
			}
		}
		
		return arr[x];
	}

	
}