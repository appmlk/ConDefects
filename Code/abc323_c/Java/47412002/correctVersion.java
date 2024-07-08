

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] scores = new int[m][2];
        String[] str = new String[n];
        for(int i = 0; i<m; i++){
          scores[i][0] = i;
          scores[i][1] = scanner.nextInt();;
        }
        long[] playerScore = new long[n];
        long max = 0;
        for(int i = 0; i<n; i++){
          String s = scanner.next();
          str[i] = s;
          long score = 0;
          boolean solved = false;
          for(int j = 0; j<m; j++){
            if(s.charAt(j)=='o'){
              score += (long)scores[j][1];
              solved = true;
            }
          }
          score += (long)i;
          max = Math.max(max,score);
          playerScore[i] = score;
        }
        
      Arrays.sort(scores, (x, y) -> y[1] - x[1]);
        
        for(int i = 0; i<n; i++){
          int count = 0;
          long initial = playerScore[i];
          int k = 0;
          while(initial<max){
            long add = scores[k][1];
            int place = scores[k][0];
            if(str[i].charAt(place)=='x'){
              initial +=  add;
              count++;
            }
            k++;
          }
          System.out.println(count);
        }

    }
}
