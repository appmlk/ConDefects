import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String S = input.next();
        var frequency = new HashMap<Character, Integer>();
        char maxChar = S.charAt(0);
        for (char c : S.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            if (frequency.get(c) > frequency.get(maxChar) || ((frequency.get(c) == frequency.get(maxChar) ) && c < maxChar)){
                maxChar = c;
            }
        }
        System.out.printf("%c\n", maxChar);
        input.close();
    }
}