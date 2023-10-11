import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        String[] numList = input1.split(" ");
        String input2 = scanner.nextLine();
        String[] humanList = input2.split(" ");

        int count = 0;
        for (int i = 0; i < Integer.parseInt(numList[0]); i++){
            if (Integer.parseInt(humanList[i]) < Integer.parseInt((numList[1]))){
                count++;
            }
        }
        System.out.println(count);
    }
}
