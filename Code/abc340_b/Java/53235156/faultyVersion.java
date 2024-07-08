import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        HashMap<Integer,Long> map = new HashMap<Integer,Long>();
        int n = sc.nextInt();
        int idx=0;
        for(int i=0;i<n;i++){
            long a =sc.nextLong();
            if(a ==1){
                map.put(i+1,sc.nextLong());
                idx++;
            }else {
                int b = sc.nextInt();
                System.out.println(map.get(idx-b+1));
            }
            

        }
        
    }
}