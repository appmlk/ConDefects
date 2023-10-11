import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        
        sc.nextLine();
        StringBuilder S = new StringBuilder(sc.nextLine());
        StringBuilder T = new StringBuilder(sc.nextLine());
        
        List<String> replacablePatterns = Arrays.asList("ABC", "BCA", "CAB");

        for (int i=S.length()-3; i>-1; i--) {
            if (S.length() < i+3) {
                continue;
            }
            if (replacablePatterns.contains(S.substring(i, i+3))) {
                // S = S.substring(0, i) + S.substring(i+3);
                S.delete(i, i+3);
            }
        }
        
        for (int i=T.length()-3; i>-1; i--) {
            if (T.length() < i + 3) {
                continue;
            }
            if (replacablePatterns.contains(T.substring(i, i+3))) {
                // T = T.substring(0, i) + T.substring(i+3);
                T.delete(i, i+3);
            }
        }

        if (S.toString().contentEquals(T)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        
    }
}