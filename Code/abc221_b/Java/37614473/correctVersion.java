import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();
        boolean flag = true;
        boolean cflag = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i) && !cflag) {
                cflag = true;
                if (s.charAt(i+1) != t.charAt(i) || s.charAt(i) != t.charAt(i+1)) {
                    flag = false;
                    break;
                }
                i += 2;
            } else if(s.charAt(i) != t.charAt(i) && cflag) {
                flag = false;
                break;
            }
        }
        System.out.println(flag ? "Yes" : "No");
        sc.close();
    }
}
