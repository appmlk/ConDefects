import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        System.out.println(truncate(value));
    }


    public static String truncate(int n) {

        String myValue = String.valueOf(n);
        if (n < Math.pow(10, 3)) {
            return myValue;
        } else if (n >= Math.pow(10, 3) && n < Math.pow(10, 4)) {

            myValue = myValue.substring(0, 3) + "0";

        } else if (n >= Math.pow(10, 4) && n < Math.pow(10, 5)) {

            myValue = myValue.substring(0, 3) + "00";

        } else if (n >= Math.pow(10, 5) && n < Math.pow(10, 6)) {

            myValue = myValue.substring(0, 3) + "000";

        } else if (n >= Math.pow(10, 6) && n < Math.pow(10, 7)) {

            myValue = myValue.substring(0, 3) + "0000";

        } else if (n >= Math.pow(10, 7) && n < Math.pow(10, 8)) {

            myValue = myValue.substring(0, 3) + "00000";
        } else if (n >= Math.pow(10, 8) && n < Math.pow(10, 9)) {

            myValue = myValue.substring(0, 3) + "00000";
        }
        return myValue;
    }

    public static void firstPlayer(Scanner sc) {
        int t = sc.nextInt();
        int[] ageArr = new int[t];
        String[] personArr = new String[t];
        int smallest = Integer.MAX_VALUE;


        for (int i = 0; i < t; i++) {
            String person = sc.next();
            int age = sc.nextInt();
            ageArr[i] = age;
            personArr[i] = person;
        }


        for (int i = 0; i <= ageArr.length - 1; i++) {
            if (ageArr[i] < smallest) {
                smallest = ageArr[i];

            }
        }

        for (int i = 0; i <= ageArr.length - 1; i++) {
            if (ageArr[i] == smallest) {
                for (int j = i; j <= ageArr.length - 1; j++) {
                    System.out.println(personArr[j]);
                }

                for (int j = 0; j < i; j++) {
                    System.out.println(personArr[j]);
                }
            }
        }
    }
}

