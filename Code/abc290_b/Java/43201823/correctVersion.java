import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);

        int N= scan.nextInt();
        int K=scan.nextInt();
        String S = scan.next();
        char[] pori= S.toCharArray();
    
        for(int i=0;i<N;i++){
            if(pori[i]=='o'&&K>0){
                System.out.print("o");
                K--;
                }else{
                    System.out.print("x");
                }
            }
        }
    }

