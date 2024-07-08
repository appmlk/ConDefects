import java.util.*;

public class Main{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>();

        int N = scan.nextInt();
        int L = scan.nextInt() - 1;
        int R = scan.nextInt() - 1;
        int S = (R - L + 1) / 2;

        for(int i = 0 ; i < N ; i++){
            list.add(i + 1);
        }
        
        int comL = 0;
        int comR = 0;
        
        while(L <= R){
          comL = list.get(L);    //左を一時格納
          comR = list.get(R);    //右を一時格納
          list.set(L, comR);
          list.set(R, comL);
          L++;
          R--;
        }
        
        for(int i = 0 ; i < N ; i++){
            System.out.print(list.get(i) + " ");
        }
        
        scan.close();
    }
}