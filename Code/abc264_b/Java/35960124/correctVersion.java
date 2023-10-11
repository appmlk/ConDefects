import java.util.Scanner;

public class Main{
    public static void main(String[]args)   {
        Scanner scan=new Scanner (System.in);
        int r=scan.nextInt();
        int c=scan.nextInt();
        int i;
        for(i=1;i<=8;i++){
            int jehad=16-i;
            if(r==i||r==jehad||c==i||c==jehad){
                break;
            }
        }
        int answer=i%2;
        if(answer==0){
            System.out.println("white");
        }else{
            System.out.println("black");
        }
    }
}
