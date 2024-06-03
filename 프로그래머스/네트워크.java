import java.util.*;

class Solution {
    static boolean[] visited;

    public int solution(int n, int[][] computers) {
        visited = new boolean[n];

        int answer = 0;
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                answer += 1;
                dfs(i, n, computers);
            }
        }

        return answer;
    }

    static void dfs(int x, int n, int[][] computers) {
        visited[x] = true;

        for(int i=0; i<n; i++) {
            if(!visited[i] && computers[x][i] == 1) {
                dfs(i, n, computers);
            }
        }
    }
}