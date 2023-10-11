import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        char[] sChars = s.toCharArray();
        String t = sc.nextLine();

        for (int i = 0; i < 26; i++) {
            char[] tmp = new char[sChars.length];
            for (int j = 0; j < sChars.length; j++) {
                tmp[j] = (char) (97 + ((sChars[j] + i) % 97) % 26);;
            }
            if (String.valueOf(tmp).equals(t)) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }
}