import java.util.*;
class Main {
  public static void main(String[] args) { 
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int m=sc.nextInt();
    char[][]table=new char[n][m];
    for(int i=0;i<n;i++){
        String s=sc.next();
        for(int j=0;j<m;j++){
            table[i][j]=s.charAt(j);
        }
    }
    for(int i=0;i<n-8;i++){
        for(int j=0;j<m-8;j++){
            boolean ok=true;
            for(int k=0;k<4;k++){
                for(int l=0;l<4;l++){
                    if(k<3 && l<3){
                        if(!(table[i+k][j+l]=='#' && table[i+8-k][j+8-l]=='#')){
                            ok=false;
                        }
                    } else{
                        if(!(table[i+k][j+l]!='.' && table[i+8-k][j+8-l]=='.')){
                            ok=false;
                        }
                    }
                }
            }
            if(ok){
                System.out.println((i+1)+" "+(j+1));
            }
        }
    }
  }
}