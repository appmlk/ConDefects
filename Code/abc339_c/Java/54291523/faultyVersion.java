import java.util.*;

//計算量は　N
public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long min = 2000000000000000L;
        long passengers = 0L;
        //停車した中で、最も最初の人数との差が大きくなる時（少ない方向に）
        for(int i=0; i<N; i++){
            long A = sc.nextLong();
            passengers += A;
            if(passengers<min){
                min = passengers;
            }
        }
        
        //minが負の値の時は、初期の人数=min*(-1)
        //minが正の値または0の時は、初期の人数=0
        long ans;
        if(min>=0){
            ans = 0;
        }else{
            ans = min*(-1)+passengers;
        }
        System.out.println(ans);
    }
}
