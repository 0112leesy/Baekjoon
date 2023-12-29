import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static List<Integer> lis;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        getLIS();
        System.out.println(lis.size());
    }

    static void getLIS() {
        lis = new ArrayList<>();
        lis.add(arr[0]);
        for(int i=1; i<N; i++) {
            if(arr[i] < lis.get(lis.size()-1)) { // lis 마지막 원소보다 작은 숫자일 때,
                lis.set(binarySearch(arr[i]), arr[i]);
            } else if(arr[i] > lis.get(lis.size()-1)) { // lis 마지막 원소보다 큰 숫자일 때,
                lis.add(arr[i]); // 현재 숫자 추가
            }
        }
    }

    static int binarySearch(int target) { // target 보다 크면서 제일 가까운 수 찾기
        int l = 0;
        int r = lis.size();
        int mid = 0;
        while(l < r) {
            mid = (l + r) / 2;
            if(lis.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}