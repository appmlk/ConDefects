import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {

        sc.nextInt();

        String[] zeroOneArray = sc.next().split("");

        // String[] zeroOneArray = new StringWriter(){{for(int i=0; i < 100000; i++) write("0"); }}.toString().split("");
        List<String> zeroOneList = Arrays.asList(zeroOneArray);
        zeroOneList.remove(""); // 最初に入ってしまう空文字を削除
        
        long answer = 0;
        String zeroOrOne = null;
        long switchIndex = 0;
        long lastSwitchIndex = 1;
        long numberLength = 0;

        long ceil = 0;
        long floor = 0;
        for (String n: zeroOneList){
            switchIndex = add(switchIndex, 1);

            // 1 文字目は 1 or 0 を記録して次へ
            if (switchIndex == 1){
                zeroOrOne = n;
                continue;
            }

            // 同じ文字が続く場合は次へ
            if (zeroOrOne.equals(n)){
                continue;
            }

            // 文字が切り替わった場合
            // 切り替わり前の文字が何文字続いていたかを取得
            numberLength = subtract(switchIndex, lastSwitchIndex);

            // 切り替わり前に 0 が続いていた場合
            if ("0".equals(zeroOrOne)){
                answer = add(answer, multiply(numberLength, subtract(lastSwitchIndex, 1)));
                answer = add(answer, divide(multiply(numberLength, subtract(numberLength, 1)), 2));
                
                lastSwitchIndex = switchIndex;
                zeroOrOne = n;
                continue;
            }

            // 切り替わり前に 1 が続いていた場合
            ceil = divide(add(numberLength, 1), 2);
            floor = divide(numberLength, 2);

            if (remainder(numberLength, 2) == 1){
                answer = add(answer, add(ceil, multiply(floor, add(floor, 1))));
            } else {
                answer = add(answer, multiply(ceil, add(ceil, 1)));
            }

            // N 個目の連続文字だった場合 (N > 1)
            if (lastSwitchIndex > 1){
                answer = add(answer, multiply(floor, subtract(lastSwitchIndex, 2)));
                answer = add(answer, ceil);
            }

            lastSwitchIndex = switchIndex;
            zeroOrOne = n;
        }

        // ループを抜けた後、最後の連続文字の計算を実行
        switchIndex = add(switchIndex, 1);
        numberLength = subtract(switchIndex, lastSwitchIndex);

        if ("0".equals(zeroOrOne)){
            answer = add(answer, multiply(numberLength, subtract(lastSwitchIndex, 1)));
            answer = add(answer, divide(multiply(numberLength, subtract(numberLength, 1)), 2));

            System.out.println(answer);
            return;
        }

        ceil = divide(add(numberLength, 1), 2);
        floor = divide(numberLength, 2);

        if (remainder(numberLength, 2) == 1){
            answer = add(answer, add(ceil, multiply(floor, add(floor, 1))));
        } else {
            answer = add(answer, multiply(ceil, ceil + 1));
        }

        // N 個目の連続文字だった場合 (N > 1)
        if (lastSwitchIndex > 1){
            answer = add(answer, multiply(floor, subtract(lastSwitchIndex, 2)));
            answer = add(answer, ceil);
        }

        System.out.println(answer);
    }

    private static long add(long value1, long value2){
        BigDecimal bd1 = BigDecimal.valueOf(value1);
        BigDecimal bd2 = BigDecimal.valueOf(value2);
        return bd1.add(bd2).longValue();
    }

    private static long subtract(long value1, long value2){
        BigDecimal bd1 = BigDecimal.valueOf(value1);
        BigDecimal bd2 = BigDecimal.valueOf(value2);
        return bd1.subtract(bd2).longValue();
    }

    private static long multiply(long value1, long value2){
        BigDecimal bd1 = BigDecimal.valueOf(value1);
        BigDecimal bd2 = BigDecimal.valueOf(value2);
        return bd1.multiply(bd2).longValue();
    }

    private static long divide(long value, long devisor){
        BigDecimal bd = BigDecimal.valueOf(value, 1);
        bd.setScale(0, RoundingMode.DOWN);
        BigDecimal dbDivisor = BigDecimal.valueOf(devisor);
        return bd.divide(dbDivisor).longValue();
    }

    private static long remainder(long value, long devisor){
        BigDecimal bd = BigDecimal.valueOf(value);
        BigDecimal dbDivisor = BigDecimal.valueOf(devisor);
        return bd.remainder(dbDivisor).longValue();
    }
}
