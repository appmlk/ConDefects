import java.util.*;

class Main {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      int p = sc.nextInt()-1;
      int q = sc.nextInt()-1;
      int r = sc.nextInt()-1;
      int s = sc.nextInt()-1;
      
      int[] li = new int[n];
      int[] li2 = new int[n];
      
      for (int i=0;i<n;i++){
        int a = sc.nextInt();
      	li[i] = a;
        li2[i] = a;
      }
      
      for (int i = 0;i< q-p; i++){
      	li2[p+i] = li[r+i];
        li2[r+i] = li[p+i];
      }
      
      for (int i = 0;i< n; i++){
        System.out.print(li2[i]+" ");
      }
    }
}