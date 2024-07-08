import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Input {
    int n;
    int[] a;

    public Input() {
        Scanner scanner = new Scanner(System.in);
        this.n = scanner.nextInt();
        scanner.nextLine();
        this.a = new int[this.n];
        for (int i = 0; i < this.n-1; i++) {
            this.a[i] = scanner.nextInt();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        String result = new String();
        for (int i=1;i<n+17;i++){
            if (i%3==0){
                result = result + "x";
            } else {
                result = result + "o";
            }
        }

        System.out.println(result);
    }
}