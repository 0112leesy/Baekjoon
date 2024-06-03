import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, ArrayList<String>> hmap = new HashMap<>();
        for(int i=0; i<clothes.length; i++) {
            String name = clothes[i][0];
            String type = clothes[i][1];
            ArrayList<String> list = hmap.computeIfAbsent(type, value -> new ArrayList<>());
            list.add(name);
        }

        int answer = 1;
        for(String key : hmap.keySet()) {
            answer *= (hmap.get(key).size() + 1);
        }

        return answer-1;
    }
}