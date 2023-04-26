import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string = bufferedReader.readLine();

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            list.add(string.substring(i, string.length()));
        }
        Collections.sort(list);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i)).append('\n');
        }

        System.out.println(stringBuilder.toString());
    }

}