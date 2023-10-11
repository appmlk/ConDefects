import java.util.*;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A[] = new long[64];
        long result = 0;
        long plusNo = 4;
        for(int i = 0; i<64; i++){
            A[i] = sc.nextInt();
            if(A[i]==1){
                // 最初のみ
                if(i == 0){
                    result += 1;
                    continue;
                }
                
                if(i == 1){
                    result += 2;
                    continue;
                }
                
                result += plusNo;
 
            }
            if(i == 0 || i ==1){
               continue;
            }  

               plusNo = plusNo*2;

        }
        System.out.print(Long.toUnsignedString(result));
        
    }
}