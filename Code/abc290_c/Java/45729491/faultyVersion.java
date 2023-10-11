
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0 ; i < a ; i++){
            set.add(scan.nextInt());
        }
        int arg = b;
        for(int i = 0 ; i < a ; i++ ){
            if(!set.contains(i)){
                arg = i;
                break;
            }
        }
        System.out.println(arg);
    }
}