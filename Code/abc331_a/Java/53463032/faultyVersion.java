import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int d = sc.nextInt();

        int y = sc.nextInt();
        int month = sc.nextInt();
        int day = sc.nextInt();

        if (day == d) {
            if (month == 12) {
                month = 1;
                y += 1;
            } else {
                month += 1;
            }
            day = 1;
        } else {
            day += 1;
        }

        System.out.println(y + " " + month + " " + day);
    }
}
