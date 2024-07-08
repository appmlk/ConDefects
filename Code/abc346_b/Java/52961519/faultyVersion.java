import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
    int w = scanner.nextInt();
    int b = scanner.nextInt();
    if ( 0 == b )
    {
      if ( 0 <= w && w <= 2)
      {
        System.out.println( "Yes" );
      }
      else
      {
        System.out.println( "No" );
      }
    }
    else
    {
      int odd = b % 5;
      int base = 7 * (b / 5);
      switch (odd) {
        case 0:
            if ( 5 + base <= w && w <= 9 + base )
            {
              System.out.println( "Yes" );
            }
            else
            {
              System.out.println( "No" );
            }
            break;
        case 1:
            if ( base <= w && w <= 3 + base )
            {
              System.out.println( "Yes" );
            }
            else
            {
              System.out.println( "No" );
            }
            break;
        case 2:
            if ( 1 + base <= w && w <= 5 + base )
            {
              System.out.println( "Yes" );
            }
            else
            {
              System.out.println( "No" );
            }
            break;
        case 3:
            if ( 2 + base <= w && w <= 6 + base )
            {
              System.out.println( "Yes" );
            }
            else
            {
              System.out.println( "No" );
            }
            break;
        case 4:
            if ( 4 + base <= w && w <= 7 + base )
            {
              System.out.println( "Yes" );
            }
            else
            {
              System.out.println( "No" );
            }
            break;
      }
    }
	}
}