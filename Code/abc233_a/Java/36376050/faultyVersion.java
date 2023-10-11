import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int cnt = 0;
        while(y >= x){
            x += 10;
            cnt ++;
        }
        System.out.println(cnt);
    }
}
