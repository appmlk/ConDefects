import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        for (int i = 0; i < n; i++){
            int a = sc.nextInt();
            if (a % k == 0){
                System.out.print(a/k + " ");
            }
        }
    }
}