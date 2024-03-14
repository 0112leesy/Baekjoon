import java.util.*;

class Solution {

    static Map<String, Integer> language;
    static Map<String, Integer> position;
    static Map<String, Integer> level;
    static Map<String, Integer> food;
    static ArrayList<Integer>[][][][] data;

    public int[] solution(String[] info, String[] query) {
        // 언어 : 인덱스
        language = new HashMap<>();
        language.put("-", 0);
        language.put("cpp", 1);
        language.put("java", 2);
        language.put("python", 3);
        // 직군 : 인덱스
        position = new HashMap<>();
        position.put("-", 0);
        position.put("backend", 1);
        position.put("frontend", 2);
        // 경력 : 인덱스
        level = new HashMap<>();
        level.put("-", 0);
        level.put("junior", 1);
        level.put("senior", 2);
        // 소울푸드 : 인덱스
        food = new HashMap<>();
        food.put("-", 0);
        food.put("chicken", 1);
        food.put("pizza", 2);

        // 데이터 배열 초기화
        data = new ArrayList[4][3][3][3];
        for(int i=0; i<4; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    for(int l=0; l<3; l++) {
                        data[i][j][k][l] = new ArrayList<>();
                    }
                }
            }
        }

        // 데이터 저장
        for(String str : info) {
            String[] s = str.split(" ");
            int langIndex = language.get(s[0]);
            int posIndex = position.get(s[1]);
            int levIndex = level.get(s[2]);
            int foodIndex = food.get(s[3]);
            int point = Integer.parseInt(s[4]);
            mapData(langIndex, posIndex, levIndex, foodIndex, point);
        }

        // 데이터 정렬
        for(int i=0; i<4; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    for(int l=0; l<3; l++) {
                        Collections.sort(data[i][j][k][l], Collections.reverseOrder());
                    }
                }
            }
        }

        // query에 따라 데이터 검색
        int[] result = new int[query.length];
        for(int i=0; i<query.length; i++) {
            String[] s = query[i].split(" ");
            int langIndex = language.getOrDefault(s[0], 0);
            int posIndex = position.getOrDefault(s[2], 0);
            int levIndex = level.getOrDefault(s[4], 0);
            int foodIndex = food.getOrDefault(s[6], 0);
            int point = Integer.parseInt(s[7]);

            int cnt = binarySearch(data[langIndex][posIndex][levIndex][foodIndex], point);
            result[i] = cnt==-1?0:cnt+1;
        }

        return result;
    }

    static void mapData(int lang, int pos, int lev, int food, int point) {
        for(int i=0; i<=lang; i+=lang) {
            for(int j=0; j<=pos; j+=pos) {
                for(int k=0; k<=lev; k+=lev){
                    for(int l=0; l<=food; l+=food) {
                        data[i][j][k][l].add(point);
                    }
                }
            }
        }
    }

    static int binarySearch(ArrayList<Integer> data, int point) { // point 이상이면서 가장 작은 점수의 index
        int l = 0;
        int r = data.size()-1;
        int ans = -1;
        while(l <= r) {
            int mid = (l+r)/2;
            if(data.get(mid) >= point) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}

/*
["java backend junior pizza 150",
"python frontend senior chicken 210",
"python frontend senior chicken 150",
"cpp backend senior pizza 260",
"java backend junior chicken 80",
"python backend senior chicken 50"]


java
            backend
                        junior
                                    pizza

info[java][backend][junior][pizza]
info[0,1,2,3][0,1,2][0,1,2][0,1,2]


108 * 50,000 => 데이터 저장
108 * 50,000 log(50,000) => 데이터 정렬
log(50,000) * 100,000 => 검색

O(nlogn)의 시간복잡도
*/