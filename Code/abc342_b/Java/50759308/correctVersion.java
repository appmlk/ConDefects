import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int pnum = sc.nextInt();
        
        int [] order = new int [pnum];
        
        for(int i = 0; i < pnum; i++){
            int person = sc.nextInt();
            order[person - 1] = i;
        }
        
        int qnum = sc.nextInt();
 
        for(int i = 0; i < qnum; i++){
            int query1 = sc.nextInt();
            int query2 = sc.nextInt();
            if (order[query1 - 1] < order[query2 - 1]){
                System.out.println(query1);
            }else{
                System.out.println(query2);
            }
        }
    
    }
}
