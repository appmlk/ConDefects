import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = Utility.readInt();
		var nums = new int[N][2];
		for(int i = 0; i < N; i++) {
			nums[i][0] = Utility.readInt();
			nums[i][1] = Utility.readInt();
		}
		
		int dp1 = nums[0][1];
		int fl1 = nums[0][0];
		int dp2 = -1;
		int fl2 = -1;
		int dp_max = nums[0][1];
		for(int i = 1; i < N; i++) {
			//dp1保存当前单个最大
			//dp2用于保存第二大并且味道和dp1不同
			
			//dp_max用于保存两个的和的最大值
			if(nums[i][0] == fl1) {
				int tmp = dp1 > nums[i][1] ? dp1 + nums[i][1]/2 : dp1/2 + nums[i][1];
				dp_max = Math.max(dp_max, tmp);
			}else if(nums[i][0] == fl2) {
				int tmp = dp2 > nums[i][1] ? dp2 + nums[i][1]/2 : dp2/2 + nums[i][1];
				dp_max = Math.max(dp_max, tmp);
			}else {
				int tmp = dp1 > dp2 ? dp1 : dp2;
				dp_max = Math.max(dp_max, nums[i][1] + tmp);
			}
			
			//把dp2初始化为首个和dp1味道不同的值
			if(dp2 == -1 && nums[i][0] != fl1) {
				dp2 = nums[i][1];
				fl2 = nums[i][0];
			}
			
			
			//dp2味道不同于dp1的最大值味道
			if(nums[i][1] > dp2 && nums[i][0] != fl1) {
				dp2 = nums[i][1];
				fl2 = nums[i][0];
			}
			
			//dp1接受同味道的最大值
			if(nums[i][1] > dp1 && nums[i][0] == fl1) {
				dp1 = nums[i][1];
				fl1 = nums[i][0];
			}
			
			//判断dp1和dp2，并交换
			if(dp2 > dp1) {
				int tmp = dp1;
				dp1 = dp2;
				dp2 = tmp;
				
				tmp = fl1;
				fl1 = fl2;
				fl2 = tmp;
			}
			
			
		}
		
		System.out.println(dp_max);
		

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
