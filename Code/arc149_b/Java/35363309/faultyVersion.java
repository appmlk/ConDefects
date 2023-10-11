import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            map.put(a[i], i);
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int loc = map.get(i+1);
            swap(a, i, loc);
            map.put(i+1, i);
            map.put(a[i],loc);
            swap(b, i, loc);
        }



        int ans = 0;
        ans += n;
        ans += lengthOfLIS(b);
        System.out.println(ans);
    }

    public static int lengthOfLIS(int[] nums) {
        int maxL = 0;
        int[] dp = new int[nums.length];
        for(int num : nums) {
            // 二分法查找, 也可以调用库函数如binary_search
            int lo = 0, hi = maxL;
            while(lo < hi) {
                int mid = lo+(hi-lo)/2;
                if(dp[mid] < num)
                    lo = mid+1;
                else
                    hi = mid;
            }
            dp[lo] = num;
            if(lo == maxL)
                maxL++;
        }
        return maxL;
    }

    public static void swap(int[] nums, int i,int j) {
        int tem = nums[i];
        nums[i] = nums[j];
        nums[j] = tem;
    }
}