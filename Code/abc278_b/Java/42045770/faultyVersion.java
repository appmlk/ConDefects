import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb0 = new StringBuilder(sc.next());
    StringBuilder sb1 = new StringBuilder(sc.next());
    
    if (sb0.length() == 1) {
      sb0.insert(0, 0);
    }

    if (sb1.length() == 1) {
      sb1.insert(0, 0);
    }

    List<Character> time = new ArrayList<>();
    List<Integer> timenum = new ArrayList<>();
    
    for (int i = 0; i < 2; i++) {
      time.add(i, sb0.charAt(i));
    }
    
    for (int i = 0; i < 2; i++) {
      time.add(i + 1, sb1.charAt(i));
    }
    
    for (int i = 0; i < 4; i++) {
      timenum.add(i, Integer.parseInt(String.valueOf(time.get(i))));
    }
    
    if (timenum.get(0) == 0) {
      if (timenum.get(1) >= 6) {
        timenum.set(0, 1);
        timenum.set(1, 0);
        timenum.set(2, 0);
        timenum.set(3, 0);
      }
    }
    if (timenum.get(0) == 1) {
      if (timenum.get(1) >= 6) {
        timenum.set(0, 2);
        timenum.set(1, 0);
        timenum.set(2, 0);
        timenum.set(3, 0);
      }
    }
    if (timenum.get(0) == 2) {
      if (timenum.get(2) >= 4 && 0 <= timenum.get(1) && timenum.get(1) <= 2) {
        timenum.set(1, timenum.get(1) + 1);
        timenum.set(2, 0);
        timenum.set(3, 0);
      }
      if (timenum.get(2) >= 4 && timenum.get(1) == 3) {
        timenum.set(0, 0);
        timenum.set(1, 0);
        timenum.set(2, 0);
        timenum.set(3, 0);
      }
    }
    
    System.out.println(timenum.get(0) + "" + timenum.get(1) + " " + timenum.get(2) + "" + timenum.get(3));
  }
}
