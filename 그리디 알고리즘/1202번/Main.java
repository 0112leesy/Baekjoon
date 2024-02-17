import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer[]> jewels = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            jewels.add(new Integer[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        Collections.sort(jewels, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if(o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];
            }
        });

        ArrayList<Integer> bags = new ArrayList();
        for(int i=0; i<K; i++) {
            bags.add(Integer.parseInt(bufferedReader.readLine()));
        }
        Collections.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int j = 0;
        long ans = 0;
        for(int i=0; i<K; i++) { // 모든 가방에 대해서
            int c = bags.get(i);
            while(j < N && jewels.get(j)[0] <= c) { // 해당 보석을 넣을 수 있으면 pq에 집어넣음
                pq.offer(jewels.get(j)[1]);
                j += 1; // 한 번 검사한 보석은 지나감
            }
            if(pq.size() > 0) { // 우선순위큐에 있는 보석의 가격을 뽑음
                ans += pq.poll();
            }
        }

        System.out.println(ans);
    }
}