import java.io.IOException;
import java.util.Scanner;


/*

    4 2
    1 2 1 2
    6 3 4 5



    check t color is there
      if t max(t's rank)
    check the color of 1person
      max(p1 colors high rank)

      if'

 */
public class Main {
     public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        String str = s.next();

        boolean good = false;

        for(int i = 0; i < n; i++) {
            if(str.charAt(i) == 'x') {
                System.out.println("No");
                return;
            } else if(str.charAt(i) == 'o')
                good = true;
        }

        if(good)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}