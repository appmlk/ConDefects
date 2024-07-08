import java.util.Scanner;

class Main{

  public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        
        String text;
        String gridA[][] = new String[n][];
        String gridB[][] = new String[n][];
        int line = 0;
        int raw = 0;
        String strLineA[] = new String[n];
        String strLineB[] = new String[n];

        int count = n;

        while (count>0){
           text = scanner.next();
           strLineA[n-count] = text; 
           gridA[n-count] = text.split("");
           count = count - 1;
        }

        count = n;

        while (count>0) {
            text = scanner.next();
            strLineB[n-count] = text;
            gridB[n-count] = text.split("");
            count = count - 1;
        }

        scanner.close();
        count = n;

        while(count>0){
          /*
           *         System.out.println("line");
        System.out.println(strLineA[n-count]);
        System.out.println(strLineB[n-count]);
           */

          if (!strLineA[n-count].equals(strLineB[n-count])){
            /*
             * System.out.println("break");
             */
            
            line = n - count + 1;
            break;
          }
          count = count - 1;
        }
       
        count = n;

        while(count>0){
          /*
           * System.out.println("raw");
           */
            
          if (!gridA[line-1][n-count].equals(gridB[line-1][n-count])){
            raw = n - count + 1;
            break;
          }
          count = count - 1;
        }

        System.out.println(raw + " " + line);

    }
}