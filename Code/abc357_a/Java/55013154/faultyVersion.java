import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int count =0;

        for(int i = 0; i < n; i++){
            int tmp = sc.nextInt();
            if(m  >= tmp){
                m -= tmp;
                count++;
            }
        }     
        System.out.println(count);   
    }
}