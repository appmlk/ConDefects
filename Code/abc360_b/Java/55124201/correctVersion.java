import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String S = scan.next();
        String T = scan.next();

        // Divide the string into substrings of length w
        for (int w = 1; w < S.length(); w++) {
            for (int c = 0; c < w; c++) {
                String result = "";
                // Concatenate the c-th character from each substring
                for (int i = c; i < S.length(); i += w) {
				    result += S.substring(i,i+1);
				}
                if (result.equals(T)) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
     }
}