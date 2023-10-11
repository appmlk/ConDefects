import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        int []A=new int[8];
        int ansd,ans;
        ansd=ans=0;
        for(int i=0; i<8; i++){
            A[i]=sc.nextInt();
        }
        
        for(int i=0; i<7; i++){
            if(A[i]<=A[i+1])ansd++;
        }
        if(ansd==7)ans++;
       
        if(100<=A[0] && A[7]<=675)ans++;
        
        ansd=0;
        for(int i=0; i<8; i++){
            if(A[i]%25==0)ansd++;
        }
        if(ansd==8)ans++;
        
        if(ans==3){
            System.out.print("Yes");
        }else{
            System.out.print("No");
        }
    }
}