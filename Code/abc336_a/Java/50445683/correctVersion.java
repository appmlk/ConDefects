import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String ans = "L";
        for(int i=0; i<n; i++) {
            ans += "o";
        }
        ans += "ng";
        System.out.println(ans);
        input.close();
    }
}
