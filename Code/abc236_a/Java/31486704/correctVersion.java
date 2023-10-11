import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner in = new Scanner(System.in);
        
        char[] arr = in.next().toCharArray();
        int a = in.nextInt();
        int b = in.nextInt();
        
        char tmp = arr[a - 1];
        arr[a - 1] = arr[b - 1];
        arr[b - 1] = tmp;
        
        System.out.println(new String(arr));
    }
}
