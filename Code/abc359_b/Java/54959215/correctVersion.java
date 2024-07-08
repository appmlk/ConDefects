
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int a[] = Arrays.stream(new int[n*2]).map(e -> sc.nextInt()).toArray();

        int count = 0;
        for(int i = 0; i < 2*n - 2; i++) {
            if(a[i] == a[i + 2]) {
                count++;
            }
        }

        System.out.println(count);
    }
}