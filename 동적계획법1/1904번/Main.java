import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
	
	static int arr[] = new int[1000001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for(int i=0; i<arr.length; i++) arr[i] = -1;
		arr[0] = 0;
		arr[1] = 1;
		arr[2] = 2;

		System.out.println(tile(N));
		
	}

	static int tile(int n) {

		if(arr[n] == -1) {
			arr[n] = (tile(n-1) + tile(n-2)) % 15746; //모듈러 연산의 분배법칙 (A+B)%C = (A%C + B%C)%C 
		}
		
		return arr[n]; 
	}
	
}