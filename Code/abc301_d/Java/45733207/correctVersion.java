import java.util.*;
public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        long n = sc.nextLong();
        List<Integer> pos = new ArrayList<>();
        long number = 0;
        for (int i=0;i<s.length();i++){
            number = number<<1;
            char c = s.charAt(i);
            if (c=='1'){
                number = number|1;
            }else if (c=='?'){
                pos.add(i);
            }

        }

        if (number>n) System.out.println(-1);
        else {
            for (var p:pos){
                p = s.length()-p-1;
                if ((number|(1L<<p))<=n){
                    number = number| (1L <<p);
                }
            }
            System.out.println(number);
        }




    }
}