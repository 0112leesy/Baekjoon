import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = bufferedReader.readLine();

        TreeMap<Character, Integer> map = buildMap(name);

        if(!checkName(map, name)) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        String answer = "";
        for(char c : map.keySet()) {
            for(int i=0; i<(map.get(c) / 2); i++) {
                answer += c;
            }
        }

        String temp = answer;

        for(char c : map.keySet()) {
            if(map.get(c) % 2 == 1) {
                answer += c;
                break;
            }
        }

        for(int i=0; i<temp.length(); i++) {
            answer += temp.charAt(temp.length()-1-i);
        }
        System.out.println(answer);

    }

    static TreeMap<Character, Integer> buildMap(String name) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        for(int i=0; i<name.length(); i++) {
            char c = name.charAt(i);
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }
        return map;
    }

    static boolean checkName(TreeMap<Character, Integer> map,  String name) { // 팰린드롬을 만들 수 있는지 없는지 체크
        boolean flag = false;
        for(char c : map.keySet()) {
            if(map.get(c) % 2 != 0) { // 알파벳이 홀수 번 나왔을 때
                if(!flag) { // flag 가 false 이면 갱신
                    flag = true;
                }
                else { // flag 가 true 이면 팰린드롬 불가 - 홀수 번 나온 알파벳이 두 개 이상
                    return false;
                }
            }
        }
        return true;
    }

}