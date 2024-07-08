import java.util.*;

public class Main{

    public static void main(String[] args){
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        int r=(int)Math.pow(3,n);
        char[][] ans=new char[r][r];
        for(int i=0;i<r;i++){
            for(int j=0;j<r;j++){
                ans[i][j]='#';
                if(i%3==1&&j%3==1) ans[i][j]='.';
                else{
                    for (int k = 3; k <= r/3; k *= 3) {
                        if (i/k%3 == 1 && j/k%3 == 1) {
                            ans[i][j]='.';
                            break;
                        }
                    }
                }
            }
        }
        for(int i=0;i<r-1;i++) System.out.println(new String(ans[i]));
        System.out.print(new String(ans[r-1]));

    }

}