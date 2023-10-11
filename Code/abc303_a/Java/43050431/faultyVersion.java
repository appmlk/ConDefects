import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int length = scanner.nextInt();

        String input1 = scanner.next();
        ;
        String input2 = scanner.next();

        if (input2.length() != length || input1.length() != length) {
            System.out.println("No");
            return;
        }


        for (int i = 0; i < length; i++) {
            if (((input1.charAt(i) == 'l' && input1.charAt(i) == '1') || (input1.charAt(i) == '1' && input1.charAt(i) == 'l')) || ((input1.charAt(i) == 'o' && input1.charAt(i) == '0') || (input1.charAt(i) == '0' && input1.charAt(i) == 'o')) || input1.charAt(i) == input2.charAt(i)) {

            } else {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");

    }
}