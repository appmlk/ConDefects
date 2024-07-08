import java.util.Scanner;

public class Main {
    static int[] nums = {1,2,3,4,5,6,7,8,9,11,22,33,44,55,66,77,88,99};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int ct = 0;
        for (int i=1;i<=n;i++) {
            int val = scanner.nextInt();
            if (containsNumber(i)) {
                String s = i+"";
                if (s.length()==2 && i<=val) {
                    ct += 2;
                } else if (s.length()==1 && Integer.parseInt(i+""+i)<=val) {
                    ct += 2;
                } else if (s.length()==1 && i<=val) {
                    ct++;
                }
            }
        }
        System.out.println(ct);
    }
    static boolean containsNumber(int num) {
        for (int i: nums) {
            if (i==num)
                return true;
        }
        
        return false;
    } 
}