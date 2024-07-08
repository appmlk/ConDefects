import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        for(int i =1;i<=a;i++){
            int num = sc.nextInt();
            if(num%b==0){
                System.out.print((num/b)+" ");
            }
        }

    }
}
