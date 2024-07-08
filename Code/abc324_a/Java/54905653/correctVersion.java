import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(), i = 1, a = sc.nextInt();
    for(; i<n; i++) if(a != sc.nextInt()) break;
    System.out.print(i == n ? "Yes" : "No");
  }
}