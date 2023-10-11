import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sn = new Scanner(System.in);
    int n = sn.nextInt();
    int k = sn.nextInt();
    List<Integer> a = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      a.add(sn.nextInt());
    }

    for (int i = 0; i < k; i++) {
      a.remove(0);
      a.add(0);
    }


    sn.close();

    for (int nItem : a) {
      System.out.print(nItem + " ");
    }
  }
}