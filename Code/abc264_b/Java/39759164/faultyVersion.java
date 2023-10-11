
import java.util.*;
 
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean[][] bool = new boolean[15][15];
        for(int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                if(i==0 || i==14) bool[i][j] = true;
                else if(j==0 || j==14) bool[i][j] = true;
                else if((i==2 || i==12) && (j>=2 && j<=12)) bool[i][j] = true;
                else if((j==2 || j==12) && (i>=2 && i<=12)) bool[i][j] = true;
                else if((i==4 || i==10) && (j>=4 && j<=10)) bool[i][j] = true;
                else if((j==4 || j==10) && (i>=4 && i<=12)) bool[i][j] = true;
                else if((i==6 || i==8) && (j>=6 && j<=8)) bool[i][j] = true;
                else if((j==6 || j==8) && (i>=6 && i<=8)) bool[i][j] = true;
                else bool[i][j] = false;
            }
        }
        int r = sc.nextInt()-1;
        int c = sc.nextInt()-1;
        if(bool[r][c]) System.out.println("black");
        else System.out.println("White");
        sc.close();
    }
}
