import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input1 = scan.nextLine();
		String[] nums = input1.split("\\s");
		int numOfAlien = Integer.parseInt(nums[0]);
		int maxCleanableHand = Integer.parseInt(nums[1]);
		String input2 = scan.nextLine();
		String[] hands = input2.split("\\s");
		scan.close();
		List<Integer> list = new ArrayList<>();
		for (String hand : hands) {
			list.add(Integer.parseInt(hand));
		}
		int count = 0;
		int num = 1;
		while (maxCleanableHand > 0) {
			maxCleanableHand = maxCleanableHand - list.get(count);
			if (maxCleanableHand >= 0) {
				count++;
				num++;
			}
			if (num > numOfAlien) {
				break;
			}
		}
		System.out.println(count);
	}
}