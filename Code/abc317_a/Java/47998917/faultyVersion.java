import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int N=Integer.parseInt(scanner.next());
        int H=Integer.parseInt(scanner.next());
        int X=Integer.parseInt(scanner.next());
        scanner.nextLine();

        for(int i=0;i<N;i++){
            int P=Integer.parseInt(scanner.next());
            if(H+P>X){
                System.out.println(i+1);
                return;
            }

        }

    }
}