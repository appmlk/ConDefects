import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        str.replace("a", "").replace("e", "").replace("i", "").replace("u", "").replace("o", "");
        System.out.println(str);
    }
}