import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class point { // point 클래스: x좌표, y좌표 값을 가짐
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
        Collections.sort(arr, new Comparator<point>() { // point 객체가 담긴 arr 정렬

			@Override // Comparator 내 compare 함수를 override 하여 정렬 기준 설정하기
            // point 객체 2개를 비교하여 크면 양수, 같으면 0, 작으면 음수를 리턴하도록 함
			public int compare(point p1, point p2) {
				if(p1.x > p2.x) return 1;
				else if(p1.x == p2.x && p1.y > p2.y) return 1;
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