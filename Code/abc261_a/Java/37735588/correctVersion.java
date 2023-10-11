import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int[] f = new int[110];
        for (int i = Math.min(a,b); i < Math.max(a,b); i++) {
            f[i]++;
        }
        for (int i = Math.min(c,d); i <Math.max(c,d); i++) {
            f[i]++;
        }
        int res=0;
        for (int i = 0; i < f.length; i++) {
            if(f[i]==2)res++;
        }
        System.out.println(res);
    }
}