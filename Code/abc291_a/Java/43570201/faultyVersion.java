import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        char a[] = sc.next().toCharArray();
        int ans = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] >= 'A' && a[i] <= 'Z') {
                System.out.println(i);
                return;
            }
        }
    }
}