
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int a[] = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = sc.nextInt() - 1;
        }

        ArrayList<Long>[] lists = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            lists[a[i]].add((long)i);
        }

        long result = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 1; j < lists[i].size() ; j++) {
//                System.out.println(i + " " + j);
//                result += (lists[i])
                result += (j * ((lists[i].size() - 1) - j + 1)) * ((lists[i].get(j) - lists[i].get(j - 1) - 1));
//                System.out.println("result " + result);
            }
        }

        System.out.println(result);

    }
}
