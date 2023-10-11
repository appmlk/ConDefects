
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int i = Integer.parseInt(br.readLine());
        i += 65;
        for (int j = 65; j <= i; j++) {
            char c = (char) j;
            System.out.print(c);
        }
    }
}
