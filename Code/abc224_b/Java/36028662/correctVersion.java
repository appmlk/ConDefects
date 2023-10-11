import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner scan=new Scanner (System.in);
        int h=scan.nextInt();
        int w=scan.nextInt();
        int [][]ar2=new int[h][w];
        for(int i=0;i<h;i++){
            for(int j=0; j<w ;j++){
                ar2[i][j]=scan.nextInt();
            }
        }
        String ans="Yes";
        for(int i=0;i<h;i++){
            for(int j=0; j<w ;j++){
                if(i>0){
                    if(i==j){
                        if(ar2[i][j]+ar2[i-1][j-1]<=ar2[i][j-1]+ar2[i-1][j]){
                            continue;

                        }else {
                            ans="No";
                        }
                    }
                }
            }
        }
            System.out.println(ans);

    }
}