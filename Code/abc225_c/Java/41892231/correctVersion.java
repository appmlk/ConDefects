import java.util.Scanner;
class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    boolean isTrue = true;
    int[] num = new int[M];
    for(int i=0;i<M;i++)
      num[i] = sc.nextInt()-1;
    for(int i=1;i<M;i++)
      isTrue &= num[i-1]+1==num[i]&&num[i-1]%7+1==num[i]%7;
    for(int i=1;i<N;i++){
      for(int j=0;j<M;j++){
        int B = sc.nextInt()-1;
        isTrue &= B-7==num[j];
        num[j] = B;
      }
    }
    System.out.println(isTrue?"Yes":"No");
  }
}
