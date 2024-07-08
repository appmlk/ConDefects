import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      long result = 0;
      Range[] list = new Range[n+1];
      for (int i=0; i<n; i++) {
        list[i] = new Range(sc.nextInt(), sc.nextInt());
      }
      list[n] = new Range(1000000001, 1000000001);

      // 配列を l の昇順にソート
      Arrays.sort(list, Comparator.comparingInt(value -> value.l));
      
      for (int i=0; i<n; i++) {
        int left = i;
        int right = n + 1;
        int mid;
        while (right - left > 1) {
          mid = (left + right) / 2;
          //System.out.println("i-R:"+list[i].r+"j-L:"+list[mid - 1].l);
          if (list[mid].l <= list[i].r) {
            left = mid;
          } else {
            right = mid;
          }
          //System.out.println("left:"+left+"mid:"+mid+"right:"+right);
        }
        //System.out.println("i:"+i+" left:"+left+"right:"+right);
        result += left - i;
      }
      System.out.println(result);
    }
}

class Range {
  public int l;
  public int r;
  
  Range(int l, int r) {
    this.l = l;
    this.r = r;
  }
}