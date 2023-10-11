import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = sc.nextInt();
        List<Integer> ans = new ArrayList<>();
        ans.add(a[0]);
        for(int i = 1; i < n; i++){

            int tmp = a[i] - a[i - 1];
            if(tmp > 1){
                for(int j = a[i - 1] + 1; j < a[i]; j++){
                    ans.add(j);
                }
                ans.add(a[i]);
            } else if(tmp < 0){
                for(int j = a[i - 1] - 1; j > a[i]; j--){
                    ans.add(j);
                }
                ans.add(a[i]);
            } else {
                ans.add(a[i]);
            }
        }

        for(int num: ans){
            System.out.print(num + " ");
        }
    }
}
