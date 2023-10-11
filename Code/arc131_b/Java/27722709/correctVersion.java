
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        
        int H = Integer.parseInt(sc.next());
        int W = Integer.parseInt(sc.next());
        
        int[][] canpas = new int[H][W];
        int[][] blankCanpas = new int[H*W][2];
        int blankCanpasIndex = 0;
        
        for (int i=0; i<H; i++) {
            String str = sc.next();
            for (int j=0; j<W; j++) {
                if (str.charAt(j) == '.') {
                    canpas[i][j] = 0;
                    blankCanpas[blankCanpasIndex] = new int[]{i,j};
                    blankCanpasIndex++;
                } else {
                    canpas[i][j] = Character.getNumericValue(str.charAt(j));
                }
            }
        }
        
        
        // 色塗り作業
        boolean paintable = false;
        int tryColor = 1;

        for (int scanIndex=0; scanIndex<blankCanpasIndex; scanIndex++) {
            
            int paintColor = 0;

            // 1~5を走査
            for (int i=tryColor; i<=5; i++) {
                
                if (blankCanpas[scanIndex][0]-1 > -1 && canpas[blankCanpas[scanIndex][0]-1][blankCanpas[scanIndex][1]] == i) {
                    continue;
                }
                if (blankCanpas[scanIndex][0]+1 < H && canpas[blankCanpas[scanIndex][0]+1][blankCanpas[scanIndex][1]] == i) {
                    continue;
                }
                if (blankCanpas[scanIndex][1]-1 > -1 && canpas[blankCanpas[scanIndex][0]][blankCanpas[scanIndex][1]-1] == i) {
                    continue;
                }
                if (blankCanpas[scanIndex][1]+1 < W && canpas[blankCanpas[scanIndex][0]][blankCanpas[scanIndex][1]+1] == i) {
                    continue;
                }
                
                paintable = true;
                paintColor = i;
                break;
            }
            
            if (!paintable) {
                tryColor = canpas[blankCanpas[scanIndex-1][0]][blankCanpas[scanIndex-1][1]] + 1;
                scanIndex--;
            } else {
                canpas[blankCanpas[scanIndex][0]][blankCanpas[scanIndex][1]] = paintColor;
                tryColor = 1;
            }
            

            paintable = false;
        }


        for (int[] line : canpas) {
            for (int math : line) {
                System.out.print(math);
            }
            System.out.println();
        }
    }
}