import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String tmp=sc.nextLine();
        int N=Integer.parseInt(tmp);

        tmp=sc.nextLine();
        String[] W=tmp.split(" ");
        String[] S={ "and", "not", "that", "the", "you"};
        for(int i=0;i<N;i++){
            for(int j=0;j<S.length;j++){
                if( W[i].equals(S[j]) ){
                    System.out.println("Yes");
                    return ;
                }
            }
        }
        System.out.println("No");
        return ;
    }

    /*private static int[][] rotate(int n, int[][] mtx) {
    int[][] ret = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        ret[i][j] = mtx[n - 1 - j][i];
      }
    }
    return ret;
  }
    */
}