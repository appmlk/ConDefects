import java.util.*;
import java.io.*;
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long a = sc.nextLong();
    long b = sc.nextLong();
    long ans = 500000000l * b + a;
    System.out.println(ans);
  }
}