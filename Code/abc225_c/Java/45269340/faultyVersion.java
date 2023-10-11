import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int m = entrada.nextInt();
        int n = entrada.nextInt();
        int [][] mat = new int[m][n];
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                mat[i][j]= entrada.nextInt();               
            }
        }
        int x = mat[0][0];
        boolean sen = true;
        for (int i = 1; i < n && sen; i++) {
            sen = x+i ==  mat[0][i];
        }
        if (!sen) {

            System.out.println("No");
            return;
        }
        
        while((x-1)%7 != 0){
            x--;
        }
        if (mat[0][n-1] >= x+8 ) {

            System.out.println("No");
        }else{
            sen=true;
            for (int i = 1; i < m && sen; i++) {
                for (int j = 0; j < n && sen ; j++) {
                    sen = mat[i-1][j]+7 == mat[i][j];
                    
                }
            }
            if (sen) {
                
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        

    }
    
}