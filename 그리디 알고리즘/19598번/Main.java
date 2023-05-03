import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<Integer[]> schedules;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        schedules = new ArrayList<>();

        StringTokenizer stringTokenizer;
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            schedules.add(new Integer[]{start, end});
        }

        Collections.sort(schedules, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        System.out.println(getAnswer());


    }

    static int getAnswer() {
        ArrayList<Integer> endTimes = new ArrayList<>();
        int cnt = 0;

        for (int i = 0; i < schedules.size(); i++) {
            int start = schedules.get(i)[0];
            int end = schedules.get(i)[1];

            boolean flag = false;
            for (int j = 0; j < endTimes.size(); j++) {
                if (start >= endTimes.get(j)) {
                    endTimes.set(j, end);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                endTimes.add(end);
                cnt++;
            }
        }

        return cnt;
    }

}