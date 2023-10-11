
import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        int ans = n*(n+1)*2;
        for(int i=0;i<n*4-1;i++){
            ans-=scan.nextInt();

        }
        System.out.println(ans);
    }
}

