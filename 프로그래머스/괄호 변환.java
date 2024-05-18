import java.util.*;

class Solution {
    public String solution(String p) {
        if(isCorrect(p)) {
            return p;
        }
        return modify(p);
    }

    static String modify(String p) {
        if(p.isEmpty()) {
            return p;
        }

        String[] splited = split(p);
        String u = splited[0];
        String v = splited[1];

        if(isCorrect(u)) {
            return u + modify(v);
        }

        String newstr = "(";
        newstr += modify(v);
        newstr += ")";
        newstr += alter(u);
        return newstr;
    }

    static String[] split(String p) {
        String[] splited = new String[2];
        splited[0] = "";
        splited[1] = "";
        for(int i=1; 2*i<=p.length(); i++) {
            String substr = p.substring(0, 2*i);
            if(isBalanced(substr)) {
                splited[0] = substr;
                splited[1] = p.substring(2*i, p.length());
                break;
            }
        }
        return splited;
    }

    static boolean isBalanced(String p) {
        int count1 = 0;
        int count2 = 0;
        for(int i=0; i<p.length(); i++) {
            if(p.charAt(i) == '(') {
                count1 += 1;
            } else {
                count2 += 1;
            }
        }

        if(count1 == count2) {
            return true;
        }
        return false;
    }

    static boolean isCorrect(String p) {
        if(isBalanced(p)) {
            Stack<Character> stack = new Stack();
            for(int i=0; i<p.length(); i++) {
                if(p.charAt(i) == '(') {
                    stack.push('(');
                } else {
                    if(stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
            }
            if(stack.isEmpty()) {
                return true;
            }
            return false;
        }
        return false;
    }

    static String alter(String u) {
        String substr = u.substring(1, u.length()-1);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<substr.length(); i++) {
            if(substr.charAt(i) == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        return sb.toString();
    }
}