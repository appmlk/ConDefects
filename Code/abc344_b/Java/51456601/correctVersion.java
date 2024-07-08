import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> inputStrings = new ArrayList<>();

        while (sc.hasNext()) {
            String capture = sc.next();
            inputStrings.add(capture);

            if (capture.equals("0")) {
                break;
            }
        }

        for (int i = inputStrings.size() - 1; i >= 0 ; i--) {
            System.out.println(inputStrings.get(i));
        }

    }

}
