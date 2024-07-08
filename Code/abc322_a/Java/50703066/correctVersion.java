import java.util.*;
public class Main{
    public static void main(String arg[]){
        Scanner sc = new Scanner(System.in);
        int N;
        N = sc.nextInt();
        String S = sc.next();
        char[] SS =new char[N];
        int i;
        for( i=0; i<N; i++) SS[i] = S.charAt(i);
        for(int j=0; j<N-1; j++){
            if(SS[j]=='A'&&SS[j+1]=='B'&&SS[j+2]=='C'){

                System.out.println(+j+1);
                System.exit(0);
            }else{
                i=-1;
            }
        
        }
        if(i==-1) System.out.println(+i);
    }
}