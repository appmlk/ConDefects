import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
     int n = sc.nextInt();
     // 
     int[][] result = new int[n+1][n+1];
     // R→D→L→Uの順番
     char dir = 'R';
     int max = n * n-1;
     // X軸の進行方向
     // R→D→L→Uの順番
     int[] xDir = new int[4];
     // Y軸の進行方向
     // R→D→L→Uの順番
     int[] yDir = new int[4];
     // R方向
     xDir[0] = 0;
     yDir[0] = 1;
     // D方向
     xDir[1] = 1;
     yDir[1] = 0;
     // L方向
     xDir[2] = 0;
     yDir[2] = -1;
     // U方向
     xDir[3] = -1;
     yDir[3] = 0;
     
     // 今の方向 Rスタート
     int tmpDir = 0;
     
     // (1,2)スタートにする。
     int x = 1;
     int y = 1;
     // 最初だけ入れておく
     result[1][1] = 1;
     
     // 今の大きさ
     int tmp = 2;
     while(tmp <= max){
      // System.out.println(tmp);
      // System.out.println(x + " " + y);
       // 次の場所を見る
       int nextX =x + xDir[tmpDir];
       int nextY =y + yDir[tmpDir];
       if(nextX > 0 && nextX <= n && nextY > 0 && nextY <= n && result[nextX][nextY] == 0){
         // 次に進める時は方向を変えない
       }else{
         // 次に進めない時は方向を変える
         tmpDir = (tmpDir + 1) % 4; 
       }
       x += xDir[tmpDir];
       y += yDir[tmpDir];
       result[x][y] = tmp;
       tmp++;
     }
     for(int i = 1;i <= n;i++){
       for(int j = 1;j<= n;j++){
         if(i == (n+1)/2 && j == (n+1)/2){
           System.out.print("D" + " ");
         }else{
         System.out.print(result[i][j] + " ");
         }
       }
       System.out.println("");
     }
  }
}