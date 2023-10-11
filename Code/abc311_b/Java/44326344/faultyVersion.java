import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int d = Integer.parseInt(sc.next());
        String s[] = new String[n];

        for (int i=0;i<n;++i){
            s[i] = sc.next();
        }

        int cnt = 0;
        int cntf = 0;

        for (int i=0;i<d;++i){
            for (int j=0;j<n;++j){
                if(s[j].charAt(i) != '○'){
                    cnt = 0;
                    break;
                }
                ++cnt;
                if (cnt % n == 0 && (cnt / n) > cntf){
                    cntf = cnt/n;
                }
            }

        }
        System.out.println(cntf);
    }
}