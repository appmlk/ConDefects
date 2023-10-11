import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        char[] cs = sc.nextLine().toCharArray();
        long n = sc.nextLong();
        long res=0;
        int len=cs.length;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i]=='1'){
                res+=((long) 1<<(len-i-1));
            }
        }
        if (res>n){
            System.out.println(-1);
            return;
        }
        for (int i = 0; i < cs.length; i++) {
            if (cs[i]=='?'){
                if (res+((long)1<<(len-i-1))-n<0){
                    res+=((long)1<<(len-i-1));
                }
            }
        }
        System.out.println(res);
    }
}