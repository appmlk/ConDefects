import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        String[] arr = new String[n];
        for(int i = 0; i < n; i++)
            arr[i] = s.next();
        for (int i = 0; i < n; i++) {
            String c = arr[i];
            if(c.equals("and") || c.equals("not") || c.equals("that") || c.equals("the") || c.equals("you")) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }
}