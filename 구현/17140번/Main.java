import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int r, c, k;
    static final int MAX_ITERATION = 100;
    static int iterationCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[3][3];
        for(int i=0; i<3; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for(int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(checkCondition(arr, r, c)) {
            iterationCount += 1;
            arr = calculate(arr);
        }

        System.out.println(iterationCount);
    }

    static boolean checkCondition(int[][] arr, int r, int c) {
        if(iterationCount > MAX_ITERATION) {
            iterationCount = -1;
            return false;
        }
        if(arr.length < r || arr[0].length < c) {
            return true;
        }
        return arr[r-1][c-1] != k;
    }

    static int[][] calculate(int[][] arr) {
        int maxSize = 0;
        ArrayList<ArrayList<Integer>> next = new ArrayList<>();
        if(isRowMore(arr)) {
            for(int i=0; i<arr.length; i++) {
                next.add(new ArrayList<>());
            }

            for(int i=0; i<arr.length; i++) {
                HashMap<Integer, Integer> map = new HashMap<>();
                for(int j=0; j<arr[0].length; j++) {
                    if(arr[i][j] == 0) continue;
                    map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
                }

                maxSize = getMaxSize(maxSize, next, i, map);
            }

            int r = Math.min(100, arr.length);
            int c = Math.min(100, maxSize);
            int[][] nextArr = new int[r][c];
            for(int i=0; i<r; i++) {
                for(int j=0; j<c; j++) {
                    if(next.get(i).size() == j) break;
                    nextArr[i][j] = next.get(i).get(j);
                }
            }

            return nextArr;
        } else {
            for(int i=0; i<arr[0].length; i++) {
                next.add(new ArrayList<>());
            }

            for(int i=0; i<arr[0].length; i++) {
                HashMap<Integer, Integer> map = new HashMap<>();
                for(int j=0; j<arr.length; j++) {
                    if(arr[j][i] == 0) continue;
                    map.put(arr[j][i], map.getOrDefault(arr[j][i], 0) + 1);
                }

                maxSize = getMaxSize(maxSize, next, i, map);
            }

            int c = Math.min(100, arr[0].length);
            int r = Math.min(100, maxSize);
            int[][] nextArr = new int[r][c];
            for(int i=0; i<c; i++) {
                for(int j=0; j<r; j++) {
                    if(next.get(i).size() == j) break;
                    nextArr[j][i] = next.get(i).get(j);
                }
            }

            return nextArr;
        }
    }

    private static int getMaxSize(int maxSize, ArrayList<ArrayList<Integer>> next, int i, HashMap<Integer, Integer> map) {
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys,
                (o1, o2) -> map.get(o1) == map.get(o2) ? o1 - o2 : map.get(o1)-map.get(o2));

        for(Integer key : keys) {
            next.get(i).add(key);
            next.get(i).add(map.get(key));
        }

        maxSize = Math.max(maxSize, next.get(i).size());
        return maxSize;
    }

    static boolean isRowMore(int[][] arr) {
        return arr.length >= arr[0].length;
    }
}

/*
    1 2 2
    1 2 1
    1 2 3
    1 2 4
    1 2 5

    1   2   1
    2   1   3
    3   3   3

    2   1   1   2   0   0
    1   1   2   1   3   1
    3   3   0   0   0   0

    1   3   1   1   3   1
    1   1   1   1   1   1
    2   1   2   2   0   0
    1   2   1   1   0   0
    3   0   0   0   0   0
    1   0   0   0   0   0

    3   2   1   4
    1   6   0   0
    1   1   2   3
    2   1   1   3
    3   1   0   0
    1   1   0   0


    3 3 3
    1   1   1
    1   1   1
    1   1   1

    1   3   0
    1   3   0
    1   3   0

    1   1   3   1
    1   1   3   1
    1   1   3   1
 */