import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        int h = scan.nextInt();
        char[][] a = new char[n][h];
        int[] b = new int[2];
        int[] c = new int[2];
        int k=0;

        for(int i=0;i<n;i++){
            a[i] = scan.next().toCharArray();
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<h;j++){
                if(a[i][j]=='o'){
                    b[k] = i;
                    c[k] = j;
                    k++;
                }
            }
        }
        System.out.println((int)Math.abs(b[0]-b[1])+(int)Math.abs(c[0]+c[1]));
    }
}
