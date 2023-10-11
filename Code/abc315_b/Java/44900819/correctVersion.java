
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = Utility.readInt();
		int[] month = new int[N];
		int sum = 0;
		for(int i = 0; i < N; i++) {
			month[i] = Utility.readInt();
			sum += month[i];
		}
		
		int remain = (sum + 1) / 2;
		for(int i = 0; i < N; i++) {
			if(remain <= month[i]) {
				System.out.println((i+1) + " " + remain);
				break;
			}
			remain -= month[i];
		}
		
		

	}

}

class Utility {
    // Static properties...
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Function: Read a menu selection input from the keyboard, value: range from 1 to 5.
     * @return 1 to 5
     */
    public static char readMenuSelection() {
        char c;
        for (;;) {
            String str = readKeyBoard(1, false); // Contains a single character string
            c = str.charAt(0); // Convert the string to a single character of char type
            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5') {
                System.out.print("Selection error, please re-enter: ");
            } else break;
        }
        return c;
    }

    /**
     * Function: Read a character input from the keyboard.
     * @return A character
     */
    public static char readChar() {
        String str = readKeyBoard(1, false); // Single character input
        return str.charAt(0);
    }

    /**
     * Function: Read a character input from the keyboard. If Enter is pressed, return the specified default value; otherwise return the entered character.
     * @param defaultValue Specified default value
     * @return Default value or the entered character
     */
    public static char readChar(char defaultValue) {
        String str = readKeyBoard(1, true); // Either an empty string or a single character
        return (str.length() == 0) ? defaultValue : str.charAt(0);
    }

    /**
     * Function: Read an integer input from the keyboard, length less than 10 digits.
     * @return Integer
     */
    public static int readInt() {
        int n = scanner.nextInt();
        return n;
    }

    /**
     * Function: Read an integer input from the keyboard, or return the default value if Enter is pressed. Otherwise, return the entered integer.
     * @param defaultValue Specified default value
     * @return Integer or default value
     */
    public static int readInt(int defaultValue) {
        int n;
        for (;;) {
            String str = readKeyBoard(10, true);
            if (str.equals("")) {
                return defaultValue;
            }
            
            // Exception handling...
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Number input error, please re-enter: ");
            }
        }
        return n;
    }

    /**
     * Function: Read a string of specified length from the keyboard.
     * @param limit Length limit
     * @return String of specified length
     */
    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }

    /**
     * Function: Read a string of specified length from the keyboard, or return the default value if Enter is pressed. Otherwise, return the entered string.
     * @param limit Length limit
     * @param defaultValue Specified default value
     * @return String of specified length
     */
    public static String readString(int limit, String defaultValue) {
        String str = readKeyBoard(limit, true);
        return str.equals("") ? defaultValue : str;
    }

    /**
     * Function: Read a confirmation selection input from the keyboard, Y or N.
     * Wrap smaller functionality within a method.
     * @return Y or N
     */
    public static char readConfirmSelection() {
        System.out.println("Please enter your choice (Y/N): Please choose carefully.");
        char c;
        for (;;) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Selection error, please re-enter: ");
            }
        }
        return c;
    }

    /**
     * Function: Read a string.
     * @param limit Length to be read
     * @param blankReturn If true, it can read an empty string. If false, it cannot read an empty string.
     * If the input is empty or longer than the limit, it will prompt for re-entry.
     * @return String
     */
    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else continue;
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.print("Input length (cannot exceed " + limit + ") error, please re-enter: ");
                continue;
            }
            break;
        }
        return line;
    }
}
