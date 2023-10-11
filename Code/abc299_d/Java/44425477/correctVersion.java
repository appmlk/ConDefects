
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int maxQuery = 20;

        //最も右にある0
        int l = 0;
        //最も左にある1
        int r = n;

//        int a[] = new int[n];
//        Arrays.fill(a, -1);

        while(l + 1 < r) {
            int mid = (l + r)/2;

            System.out.println("? " + (mid + 1));
            int tmp = sc.nextInt();

            if(tmp == 0) {
                l = mid;
            }
            else {
                r = mid;
            }
        }

        System.out.println("! " + (l + 1));
    }
}
