import java.io.IOException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            String str = s.next();
            if(str.equals("For"))
                cnt++;
        }
        System.out.println(cnt);

        if (cnt > (n >> 1))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}