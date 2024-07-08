import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var sc = new Scanner(System.in);
        var A = sc.nextInt();
        var B = sc.nextInt();

        System.out.println(A>B?"bat":"glove");
    }
}