import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        String s = in.next();
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < n - 1; i++){
            if(s.charAt(i) == 'R'){
                int l = i;
                while(l - 1 >= 0 && s.charAt(l - 1) == 'A'){
                    l --;
                }
                int r = i;
                while(r + 1 < n && s.charAt(r + 1) == 'C'){
                    r ++;
                }
                list.add(Math.min(i - l, r - i));
            }
        }

        int sum = 0;
        for(int e : list){
            sum += e;
        }
        System.out.println(Math.min(2*list.size(), sum));

        in.close();
    }
}