import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;


public class Main {

    public static void main(String[] args) {

//        String source = "8 3 1000000000\n" +
//                "1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000\n" +
//                "\n";
        InputStream source = System.in;
        Scanner sc = new Scanner(source);

        int N = sc.nextInt();
        int D = sc.nextInt();
        int P = sc.nextInt();

        float passValue = (float) P / (float) D;

        int[] planArray = new int[N];



        int countパスを使ったほうがいい日 = 0;


        long sum = 0;
        for (int i=0; i<N; i++) {
            int F = sc.nextInt();
            sum += F;
            planArray[i] = F;
            if (passValue <= (float)F) {
                countパスを使ったほうがいい日++;
            }
        }

        if (countパスを使ったほうがいい日 == 0) {
            System.out.println(sum);
            return;
        }

        Arrays.sort(planArray);
        for (int f = 0, l = planArray.length - 1; f < l; f++, l--){
            int temp = planArray[f];
            planArray[f]  = planArray[l];
            planArray[l] = temp;
        }
        int パスを買う可能性のある最大 = countパスを使ったほうがいい日 / D + 1;

        long min1 = (long) パスを買う可能性のある最大 * P;
        int パスの枚数1パターン目 = D * パスを買う可能性のある最大;
        for (int i=パスの枚数1パターン目; i<N; i++) {
            min1 += planArray[i];
        }

        long min2 = (long) (パスを買う可能性のある最大 - 1) * P;
        int パスの枚数2パターン目 = D * (パスを買う可能性のある最大 - 1);
        for (int i=パスの枚数2パターン目; i<N; i++) {
            min2 += planArray[i];
        }

        System.out.println(min(min1, min2, sum));

    }


    /**
     * Math.minが引数2つしか受け付けないため、拡張したメソッド.
     * 配列の要素は1以上存在する前提.
     *
     * @param numbers 数値の配列
     * @return 最小値
     */
    public static long min(long... numbers) {
        long min = numbers[0];
        for (int i=1; i<numbers.length; i++) {
            min = Math.min(min, numbers[i]);
        }
        return min;
    }
}
