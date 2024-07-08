import java.util.Scanner;

public class Main{

    public static void main(String...args) {
        Scanner sc = new Scanner(System.in);
        final char[] string = sc.next().toCharArray();
        //文字列をi列目まで見たとき(個がｊ個ある
        int[][] dp=new int[string.length+1][string.length/2+1];
        dp[0][0]=1;

        final int M=string.length/2;
        for(int i=1;i<=string.length;i++){
            for(int j=0;j<M;j++){

                switch (string[i-1]){
                    case '(':{
                        if(0<j){
                            dp[i][j]=(dp[i-1][j-1]+dp[i][j])%998244353;

                        }
                        break;
                    }
                    case ')':{
                        if(j<M-1){
                            dp[i][j]=(dp[i-1][j+1]+dp[i][j])%998244353;

                        }
                        break;
                    }
                    default:{
                        if(j<M-1){
                            dp[i][j]=(dp[i-1][j+1]+dp[i][j])%998244353;
                        }
                        if(0<j){
                            dp[i][j]=(dp[i-1][j-1]+dp[i][j])%998244353;
                        }
                    }
                }
            }
            
        }
        System.out.println(dp[string.length][0]);
    }
}
