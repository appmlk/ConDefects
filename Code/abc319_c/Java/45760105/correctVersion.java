/**
< 基本的な考え方 >
以下のような場合 (リーチは区別するために a,b を追加)
1  2a 2b
3   4  5
6   7 2c

1 行目でがっかりするのは ...2a/b...2b/a...1... の順に数字が並んだ場合
3 列目でがっかりするのは ...2c/b...2b/c...1... の順に数字が並んだ場合

上記のパターンを除いた 1,2a,2b,5,2c の並べ方の数を
1,2a,2b,5,2c の並べ方全体の数で割ればよい

< 処理するにあたって >
上記の場合
a b c
d e f
g h i
というマス目の並べ方のうち
...b...c...a...
...c...b...a...
...c...i...f...
...i...c...f...
の場合を除いた並べ方の確率と同じ
マス目の数字のまま計算するよりこちらの方が実装がちょっと楽なので
こちらで処理する
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Main {
    
    private static Scanner sc = new Scanner(System.in);

    // 3*3 の二次元配列にマスを格納
    private static int[][] square = new int[3][3];

    // a, b, c, ... のマス目
    private static String[][] alphabetSquare = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}};

    // リーチの行・列の文字を格納するセット
    private static Set<String> reachStringSet = new HashSet<>();

    // NG のパターンを格納するリスト
    private static List<Pattern> ngPatternList = new ArrayList<>();

    public static void main(String[] args) {

        // 3*3 の二次元配列にマスを格納
        for (int record = 0; record < 3; record++){
            for (int column = 0; column < 3; column++){
                square[record][column] = sc.nextInt();
            }
        }
        sc.close();
        
        
        // 行内にリーチがあるかチェック
        // ある場合、NG パターンと計算に使用する文字を登録
        for (int record = 0; record < 3; record++){
            registerIfNgPattern(record, 0, record, 1, record, 2);
            registerIfNgPattern(record, 0, record, 2, record, 1);
            registerIfNgPattern(record, 1, record, 2, record, 0);
        }

        // 列内にリーチがあるかチェック
        // ある場合、NG パターンと計算に使用する文字を登録
        for (int column = 0; column < 3; column++){
            registerIfNgPattern(0, column, 1, column, 2, column);
            registerIfNgPattern(0, column, 2, column, 1, column);
            registerIfNgPattern(1, column, 2, column, 0, column);
        }

        // 斜め (＼) にリーチがあるかチェック
        // ある場合、NG パターンと計算に使用する文字を登録
        registerIfNgPattern(0, 0, 1, 1, 2, 2);
        registerIfNgPattern(0, 0, 2, 2, 1, 1);
        registerIfNgPattern(1, 1, 2, 2, 0, 0);

        // 斜め (／) にリーチがあるかチェック
        // ある場合、NG パターンと計算に使用する文字を登録
        registerIfNgPattern(0, 2, 1, 1, 2, 0);
        registerIfNgPattern(0, 2, 2, 0, 1, 1);
        registerIfNgPattern(1, 1, 2, 0, 0, 2);

        // リーチの行・列の文字 を Array に詰める
        String[] reachStringArray = reachStringSet.toArray(new String[reachStringSet.size()]);

        // 並べ方の計算の事前準備
        ArrayList<String> prepareList = new ArrayList<>();
        prepareList.add("");

        // 文字数 - 1 桁目までの全パターンを格納
        ArrayList<String> addedList = new ArrayList<>();
        for (int index = 0; index < reachStringArray.length - 1; index++){
            for (String prepareStr : prepareList){
                for (String str : reachStringArray){
                    if (prepareStr.contains(str)){
                        continue;
                    }
                    addedList.add(prepareStr + str);
                }
            }
            prepareList = (ArrayList<String>) addedList.clone();
            addedList.clear();
        }

        // 最後の文字を埋めつつ、並べ方の全数とがっかりしない並べ方の数を数える
        int allCounter = 0;
        int okCounter = 0;
        for (String prepareStr : prepareList){
            for (String str : reachStringArray){
                if (prepareStr.contains(str)){
                    continue;
                }

                allCounter++;

                boolean isOk = true;
                for (Pattern pattern : ngPatternList){
                    if (pattern.matcher(prepareStr + str).matches()){
                        isOk = false;
                        continue;
                    }
                }

                if (isOk){
                    okCounter++;
                }
            }
        }

        // リーチの行・列が 1 つも無い場合、答えは 0
        if (allCounter == 0){
            System.out.println("1.000000000");
        } else {
            double answer = BigDecimal.valueOf(okCounter).divide(BigDecimal.valueOf(allCounter), 9, RoundingMode.HALF_UP).doubleValue();
            System.out.println(answer);
        }
    }

    private static void registerIfNgPattern(int compareRec1, int compareCol1, int compareRec2, int compareCol2, int anotherRec, int anotherCol){
        if (square[compareRec1][compareCol1] == square[compareRec2][compareCol2]){
            ngPatternList.add(Pattern.compile("^.*" + alphabetSquare[compareRec1][compareCol1] + ".*" + alphabetSquare[compareRec2][compareCol2] + ".*" + alphabetSquare[anotherRec][anotherCol] + ".*$"));
            ngPatternList.add(Pattern.compile("^.*" + alphabetSquare[compareRec2][compareCol2] + ".*" + alphabetSquare[compareRec1][compareCol1] + ".*" + alphabetSquare[anotherRec][anotherCol] + ".*$"));

            reachStringSet.add(alphabetSquare[compareRec1][compareCol1]);
            reachStringSet.add(alphabetSquare[compareRec2][compareCol2]);
            reachStringSet.add(alphabetSquare[anotherRec][anotherCol]);
        }
    }
}
