import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int var = scanner.nextInt();
        int hor = scanner.nextInt();
        String check;
        int[] ans = new int[hor];
        String mark;

        for (int i = 0; i < var; i++) {
            mark = scanner.next();
            for (int j = 0; j < hor; j++) {
                check = String.valueOf(mark.charAt(j));
                if (check.equals(".")) {
                    continue;
                } else {
                    ans[j]++;
                }
            }
        }
        for (int k = 0; k < hor; k++) {
            System.out.print(ans[k]);
            if (k != var) {
                System.out.print(" ");
            }
        }

        scanner.close();
    }
}