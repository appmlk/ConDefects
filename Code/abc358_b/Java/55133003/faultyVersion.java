import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int a=sc.nextInt();
        int[] t = new int[n];
        int t1=0;
        for(int i=0;i<n;i++){
            t[i]=sc.nextInt();
        }
        for(int i=0;i<n;i++){
            if(i>=1 && t[i]<a+t[i-1]){
                t[i]=t[i-1]+a;
                System.out.println(t[i]);
            }
            else{
                t[i]=t[i]+a;
                System.out.println(t[i]);
            }
        }
    }
}