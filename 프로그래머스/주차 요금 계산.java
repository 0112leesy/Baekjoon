import java.util.*;

class Solution {

    static Map<String, ArrayList<String>> hmap;
    static int defaultTerm;
    static int defaultFee;
    static int unitTerm;
    static int unitFee;

    public int[] solution(int[] fees, String[] records) {
        hmap = new HashMap<>();
        defaultTerm = fees[0];
        defaultFee = fees[1];
        unitTerm = fees[2];
        unitFee = fees[3];

        for(int i=0; i<records.length; i++) {
            String[] rec = records[i].split(" ");
            String inoutInfo = rec[0] + " " + rec[2];
            String name = rec[1];

            ArrayList<String> list;
            if(hmap.containsKey(name)) {
                list = hmap.get(name);
            } else {
                hmap.put(name, new ArrayList<>());
                list = hmap.get(name);
            }
            list.add(inoutInfo);
        }

        ArrayList<String> names = new ArrayList<>(hmap.keySet());
        Collections.sort(names);

        int[] answer = new int[names.size()];
        for(int i=0; i<names.size(); i++) {
            answer[i] = pay(names.get(i));
        }


        return answer;
    }

    static int pay(String car) {
        int totalPay = 0;
        ArrayList<String> info = hmap.get(car);

        int totalTime = 0;
        for(int i=0; i<info.size()-1; i+=2) {
            String[] in = info.get(i).split(" ");
            String[] out = info.get(i+1).split(" ");

            totalTime += delta(in[0], out[0]);
        }

        if(info.size() % 2 == 1) {
            totalTime += delta(info.get(info.size()-1).split(" ")[0], "23:59");
        }

        return fee(totalTime);
    }

    static int fee(int term) {
        if(term <= defaultTerm) {
            return defaultFee;
        }
        int unitCount = (term - defaultTerm) / unitTerm;
        if((term - defaultTerm) % unitTerm != 0) {
            unitCount += 1;
        }
        return defaultFee + unitCount * unitFee;
    }

    static int delta(String in, String out) {
        return convert(out) - convert(in);
    }

    static int convert(String time) {
        String[] str = time.split(":");
        int hour = Integer.parseInt(str[0]);
        int minute = Integer.parseInt(str[1]);
        return hour * 60 + minute;
    }
}
