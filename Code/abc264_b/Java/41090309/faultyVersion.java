import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        int dis = Math.max(Math.abs(x-8),Math.abs(y));
        
        if(dis % 2 == 0){
            System.out.println("white");
        }else{
            System.out.println("black");
        }     
        sc.close();
    }
}