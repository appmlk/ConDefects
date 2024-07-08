
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        char[][] matrix = new char[n][n];

        for(int i = 0; i < n; i++) {
            matrix[i] = sc.next().toCharArray();
        }

        int count = 0;
        int row[] = new int[n];
        int col[] = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 'o') {
                    row[j]++;
                    col[i]++;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 'o') {
                    count += (col[i] - 1) * (row[j] - 1);
                }
            }
        }

        System.out.println(count);
    }
}
