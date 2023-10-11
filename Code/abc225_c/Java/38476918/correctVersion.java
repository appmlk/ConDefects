import java.util.Scanner;
class Main {
  public static void main(String args[]) {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int m=sc.nextInt();
    boolean check=true;
    int[][] arr=new int[n][m];
    for(int i=0;i<n;i++) {
      for(int j=0;j<m;j++) {
        arr[i][j]=sc.nextInt();
        if(i!=0 && arr[i-1][j]+7!=arr[i][j]) check=false;
        if(j!=0 && arr[i][j-1]+1!=arr[i][j]) check=false;
      }
    }
    if((arr[0][0]%7==0 && m!=1) || arr[0][0]%7+m>8) check=false;
    System.out.println(check?"Yes":"No");
  }
}