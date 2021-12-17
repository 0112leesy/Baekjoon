import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static void SelectionSort(int[] arr, int N) {
		for(int i=0; i<N-1; i++) {
			int min = i;
			for(int j=i; j<N; j++) {
				if(arr[j]<arr[min]) min=j;
			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());
		SelectionSort(arr, N);
		for(int i=0; i<N; i++) System.out.println(arr[i]);
	}

}