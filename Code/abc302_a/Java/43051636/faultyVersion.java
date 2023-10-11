import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long stamina = scanner.nextLong();
        long attack = scanner.nextLong();
        System.out.println(stamina / attack + stamina % attack);
    }
}