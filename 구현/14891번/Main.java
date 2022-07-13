import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 이차원배열 사용 및 왼쪽/ 오른쪽 기어를 검사하는 함수를 생성하여 코드 개선 가능

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] gear1 = br.readLine().toCharArray();
		char[] gear2 = br.readLine().toCharArray();
		char[] gear3 = br.readLine().toCharArray();
		char[] gear4 = br.readLine().toCharArray();
		
		int K = Integer.parseInt(br.readLine());
		while(K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gear_num = Integer.parseInt(st.nextToken());
			int rotation = Integer.parseInt(st.nextToken());
			switch(gear_num) {
			case 1:
				if(gear1[2] != gear2[6]) {
					if(gear2[2] != gear3[6]) {
						if(gear3[2] != gear4[6]) {
							rotate_gear(gear4, -rotation);
						}
						rotate_gear(gear3, rotation);
					}
					rotate_gear(gear2, -rotation);
				}
				rotate_gear(gear1, rotation);
				break;
			case 2:
				if(gear2[6] != gear1[2]) {
					rotate_gear(gear1, -rotation);
				}
				if(gear2[2] != gear3[6]) {
					if(gear3[2] != gear4[6]) {
						rotate_gear(gear4, rotation);
					}
					rotate_gear(gear3, -rotation);
				}
				rotate_gear(gear2, rotation);
				break;
			case 3:
				if(gear3[6] != gear2[2]) {
					if(gear2[6] != gear1[2]) {
						rotate_gear(gear1, rotation);
					}
					rotate_gear(gear2, -rotation);
				}
				if(gear3[2] != gear4[6]) {
					rotate_gear(gear4, -rotation);
				}
				rotate_gear(gear3, rotation);
				break;
			case 4:
				if(gear4[6] != gear3[2]) {
					if(gear3[6] != gear2[2]) {
						if(gear2[6] != gear1[2]) {
							rotate_gear(gear1, -rotation);
						}
						rotate_gear(gear2, rotation);
					}
					rotate_gear(gear3, -rotation);
				}
				rotate_gear(gear4, rotation);
				break;
			}

		}
		int sum = 0;
		sum += gear1[0] == '0'? 0:1;
		sum += gear2[0] == '0'? 0:2;
		sum += gear3[0] == '0'? 0:4;
		sum += gear4[0] == '0'? 0:8;
		System.out.println(sum);
	}
	
	static void rotate_gear(char[] gear, int rotation) {
		if(rotation == 1) {
			char tmp = gear[7];
			for(int i=7; i>0; i--) gear[i] = gear[i-1];
			gear[0] = tmp;
		}
		else if(rotation == -1) {
			char tmp = gear[0];
			for(int i=0; i<7; i++) gear[i] = gear[i+1];
			gear[7] = tmp;
		}
	}
	
}
