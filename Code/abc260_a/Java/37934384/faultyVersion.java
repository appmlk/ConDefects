import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        for(int i = 0; i <= 1; i++){
            String hikaku = S.substring(i,i+1);
            int count = 0;
            for(int j = 0; j <= 2; j++){
                if(hikaku.equals(S.substring(j,j+1))){
                    count++;
                }
            }
            if(count == 1){
                System.out.println(hikaku);
                return;
            }
        }
        System.out.println(-1);
    }
}