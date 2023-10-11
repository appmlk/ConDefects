import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        int HH = 21;
        int MM = 0;
        int addHH = 0;
        int addMM = scan.nextInt();

        if (addMM > 60) {
            addHH = 1;
            addMM = addMM - 60;
        }

        HH = HH + addHH;
        MM = MM + addMM;

        System.out.print(HH);
        if (MM < 10) {
            System.out.print(":0");
            System.out.print(MM);
        } else {
            System.out.print(":");
            System.out.print(MM);
        }

        scan.close();
    }
}
