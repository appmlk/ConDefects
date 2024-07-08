import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static int firstItem;
    private static int seconItem;
    private static int thirdItem;
    public static void main(String[] args) {
        tld(getInputItem());
        sc.close();
    }

    // 標準入力から値を取得
    public static String getInputItem() {
        return sc.next();
    }
    // 標準入力から複数値を取得
    public static void getInputItems() {
        firstItem = sc.nextInt();
        seconItem = sc.nextInt();
        thirdItem = sc.nextInt();
    }
    // Stringをintに変換
    public static int stringToInt(String str) throws NumberFormatException {
        return Integer.parseInt(str);
    }
    // 複数の標準出力をcharで返す
    public static char[] readInputsAsCharArray(int num) {
        StringBuilder inputString = new StringBuilder();

        for (int i = 0; i < num; i++) {
            if (sc.hasNext()) {
                String input = sc.next();
                inputString.append(input); // 文字列を追加
                if (i < num - 1) {
                    inputString.append(' '); // スペースで区切る
                }
            }
        }
        return inputString.toString().toCharArray(); // 最終的な文字列をchar[]に変換して返す
    }

    // 文字列をChar配列に変換する
    public static char[] changeToChar(String originalStr) {
        return originalStr.toCharArray();
    }

    // 双方向矢印型の文字列であるか判定
    public static String isBidirectionalArrowString(char[] originalChar) {
        int length = originalChar.length;

        if (length < 3) return "No";
        if(originalChar[0] != '<' || originalChar[length - 1] != '>') return "No";

        for (int i = 1; i < originalChar.length - 1; i++) {
            if( originalChar[i] != '=') return "No";
        }
        
        return "Yes";
    }

    // 2 つの | の間にある文字および | を S から削除した文字列を出力
    public static String outputTrimedStr(char[] originalChar) {
        StringBuilder sb = new StringBuilder(new String(originalChar));
        int firstIndex = sb.indexOf("|");
        int secondIndex = sb.indexOf("|");
        sb.delete(firstIndex, secondIndex + 1);
        return sb.toString();
    }

    //  0以上9 以下の整数であって A+B と等しくないものをいずれかひとつ出力
    public static int outputWrongAnser(int firstItem,int secondItem) {
        int referenceNum = firstItem + secondItem;
        int countNum = 0;
        for (int i = 0; i < 11; i++) {
            if( i != referenceNum ){
                System.out.println(i);
                countNum += 1;
            }
            if(countNum == 1) {
                break;
            }
        }
        return 0;
    }
    // 頻度分布
    public static String yay(char[] originalChar) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        Map<Character, Integer> positionMap = new HashMap<>();

        for (int i = 0; i < originalChar.length; i++) {
            char ch = originalChar[i];
            if (!frequencyMap.containsKey(ch)) {
                frequencyMap.put(ch, 1);
                positionMap.put(ch, i); // 位置を保存
            } else {
                frequencyMap.put(ch, frequencyMap.get(ch) + 1);
            }
        }
        // 頻度分布を出力
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println(positionMap.get(entry.getKey()));
            }
        }
        return "";
    }
    // Past ABCs
    public static String underNum(char[] originalChar){
        StringBuilder sb = new StringBuilder(new String(originalChar));
        sb.delete(0, 3);

        String refStr = sb.toString();
        int refInt = Integer.parseInt(refStr);
        if (refInt > 350 && refInt < 0) {
            return "No";
        }
        return "Yes";
    }
    // Dentist Aoki 
    // 同じ数字が何個あるのか確かめる。
    // その数字が奇数ならN-1,偶数ならN-0
    public static int Dentist(char[] originalChar) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < originalChar.length; i++) {
            char ch = originalChar[i];
            if (!frequencyMap.containsKey(ch)) {
                frequencyMap.put(ch, 1);
            } else {
                frequencyMap.put(ch, frequencyMap.get(ch) + 1);
            }
        }
        // 出現頻度が偶数か奇数かの処理分け
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                firstItem -= 1;
            }
        }
        return firstItem;
    }
    // Print 341
    public static void alternating(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("1 " + "0 ");;
        }
        System.out.print("1");
    }
    public static void Arithmetic(int firstNum, int lastNum, int tolerance) {
        for (int i = firstNum; i <= lastNum; i+=tolerance) {
            System.out.print(i + " ");
        }
    }
    // TLD
    public static void tld(String OriginalItem) {
        StringBuilder sb = new StringBuilder(new String(OriginalItem));
        int itme = OriginalItem.indexOf(".");
        int length = itme + 1;
        sb.delete(0, length);
        System.out.println(sb);
    }
}


