import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word = input.next();
        if (word.length() == 1) {
            System.out.println(word + word + word + word + word + word);
        } else if (word.length() == 2) {
            System.out.println(word + word + word);
        } else {
            System.out.println(word + word);
        }
    }
}