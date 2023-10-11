import java.text.DecimalFormat;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sn = new Scanner(System.in);
    int a = sn.nextInt();
    int b = sn.nextInt();

    double shosu = (double)b / a;
    double result = ((double)Math.round(shosu * 1000)) / 1000;

    DecimalFormat decimalFormat = new DecimalFormat("0.000");

    String formattedNumber = decimalFormat.format(result);

    sn.close();

    System.out.println(formattedNumber);
  }
}