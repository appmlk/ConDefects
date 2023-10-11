import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

  public static void main (String[] argv) {
    Scanner s = new Scanner(System.in);
    long n = Long.parseLong(s.next());
    long k = Long.parseLong(s.next());

    while (k%10==0) {
       k /= 10;
    }

    HashSet<Long> set = new HashSet<Long>();

    long temp1 = k;
    long temp2 = Long.parseLong(new StringBuilder(Long.toString(k)).reverse().toString());

    if (temp1<=temp2) {
    while (temp1<=n || temp2<=n) {
        if (temp1<=n) {
            set.add(temp1);
        }
        if (temp2<=n) {
            set.add(temp2);
        }

        temp1 *= 10;
        temp2 *= 10;
    }
    }

    PrintWriter p = new PrintWriter(System.out);
    p.println(set.size());
    p.flush();
    p.close();
    s.close();
  }

}
