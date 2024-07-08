import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int swapCost = scanner.nextInt();
        int replaceCost = scanner.nextInt();

        String s = scanner.next();


        char[] charArray = s.toCharArray();
        long balance = getBalance(charArray);
        long ans = 0;

        if (balance > 0) {
            for (int i = 0; i < charArray.length; i++) {
                if (balance > 0 && charArray[i] == ')') {
                    charArray[i] = '(';
                    ans += replaceCost;
                    balance -= 2;
                }
            }
        } else if (balance < 0) {
            for (int i = charArray.length - 1; i >= 0; i--) {
                if (balance < 0 && charArray[i] == '(') {
                    charArray[i] = ')';
                    ans += replaceCost;
                    balance += 2;
                }
            }
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '(') {
                stack.add(c);
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.add(c);
                }
            }
        }

  

        System.out.println(ans + ((stack.size() + 1L) / 2 * Math.min(swapCost, 2 * replaceCost)));
    }

    private static long getBalance(char[] chars) {
        long balance = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                balance--;
            } else {
                balance++;
            }
        }

        return balance;
    }
}
