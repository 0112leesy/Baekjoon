import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class point {
	int x;
	int y;
	
	point(int num1, int num2) {
		x = num1;
		y = num2;
	}
}

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<point> arr = new ArrayList<>();
		StringTokenizer st;
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int num1 = Integer.parseInt(st.nextToken());
        	int num2 = Integer.parseInt(st.nextToken());
        	arr.add(new point(num1, num2));
        }
        Collections.sort(arr, new Comparator<point>() {

			@Override
			public int compare(point p1, point p2) {
				if(p1.y > p2.y) return 1;
				else if(p1.y == p2.y && p1.x > p2.x) return 1;
				else return -1;
			}
        	
        });
        StringBuilder sb = new StringBuilder();
        for(point p : arr) {
        	sb.append(p.x+" "+p.y).append('\n');
        }
        System.out.println(sb);
	}

}