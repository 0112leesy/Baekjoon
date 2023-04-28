import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] count = new int[26];
    static int[] select = new int[26];
    static int N;
    static ArrayList<Book> books;
    static int minTotalPrice;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String word = bufferedReader.readLine();
        for(int i=0; i<word.length(); i++) {
            count[word.charAt(i) - 'A']++;
        }

        N = Integer.parseInt(bufferedReader.readLine());
        books = new ArrayList<>();
        for(int i=0; i<N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int price = Integer.parseInt(stringTokenizer.nextToken());
            String subject = stringTokenizer.nextToken();
            books.add(new Book(price, subject));
        }

        minTotalPrice = Integer.MAX_VALUE;
        dfs(0, 0);
        System.out.println(minTotalPrice == Integer.MAX_VALUE? -1 : minTotalPrice);

    }

    static class Book {
        private int price;
        private String subject;

        Book(int price, String subject) {
            this.price = price;
            this.subject = subject;
        }
    }

    static void dfs(int depth, int totalPrice) {
        if(depth == N) {
            if(completed()) {
                minTotalPrice = Math.min(minTotalPrice, totalPrice);
            }
            return;
        }
        // depth 번째 책을 선택
        for(int i=0; i<books.get(depth).subject.length(); i++) {
            select[books.get(depth).subject.charAt(i) - 'A']++;
        }
        dfs(depth+1, totalPrice + books.get(depth).price);

        // depth 번째 책을 선택 X
        for(int i=0; i<books.get(depth).subject.length(); i++) { // 백트래킹
            select[books.get(depth).subject.charAt(i) - 'A']--;
        }
        dfs(depth+1, totalPrice);
    }

    static boolean completed() {
        for(int i=0; i<26; i++) {
            if(count[i] > select[i]) {
                return false;
            }
        }
        return true;
    }

}