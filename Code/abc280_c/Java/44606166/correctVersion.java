import java.util.Scanner;

public class Main{

    public static void main(String...args){
        Scanner sc=new Scanner(System.in);
        final char[] S=sc.next().toCharArray();
        final char[] T=sc.next().toCharArray();

        for(int i=0;i<S.length;i++){
            if(S[i]!=T[i]){
                System.out.println(i+1);
                return;
            }
        }
        System.out.println(T.length);
    }
}
