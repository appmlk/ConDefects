import java.util.Scanner;
import java.util.Arrays;
class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int[] nums = new int[5];
    Arrays.setAll(nums,i -> sc.nextInt());
    Arrays.sort(nums);
    System.out.println(nums[0]==nums[2]&&nums[3]==nums[4]&&nums[0]!=nums[4]?"Yes":"No");
  }
}
