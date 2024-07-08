import java.util.Scanner;
 public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int count = 0;
        int n = sc.nextInt();
        int m = sc.nextInt();
        for(int i=0;i<n;i++){
            int hand = sc.nextInt();
            m-=hand;
            if(m>=0){
                count++;
            }
        }
        System.out.print(count);
    }
} 