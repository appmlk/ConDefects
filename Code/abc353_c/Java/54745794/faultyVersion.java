import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] nums = new int[n];
        long sum = 0;
        for (int i = 0;i<n;i++) {
            nums[i] = scanner.nextInt();
            sum += nums[i];
        }
        Arrays.sort(nums);
        sum = sum * (n-1);
//        System.out.println("sum:" + sum);

        for (int i = 0;i<n-1;i++) {
            int idx = binarySearch(nums, 100000000 - nums[i], i+1);
//            System.out.println("cur num: " + nums[i] + " idx: " + idx);
            sum -= (n - idx) * 100000000;
        }
        System.out.println(sum);
    }

    private static int binarySearch(int[] nums, int target, int i) {
        int left = i;
        int right = nums.length-1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] >= target && (mid == i || nums[mid-1] < target)) {
                return mid;
            } else {
                right = mid;
            }
        }
        return nums[left] >= target ? left : left + 1;
    }

}
