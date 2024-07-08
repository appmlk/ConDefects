import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        boolean isWeakBeats = true;

        for (int i = 1; i < s.length(); i+=2) {
            if (s.charAt(i) == '1') {
                isWeakBeats = false;
                break;
            }
        }
        System.out.println(isWeakBeats ? "Yes" : "No");
    }
}