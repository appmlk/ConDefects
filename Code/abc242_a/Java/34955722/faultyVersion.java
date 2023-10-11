import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        try (
                InputStreamReader InputStreamReader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(InputStreamReader);
        ) {
            String input = bufferedReader.readLine();
            String[] strArray = input.split(" ");

            int upper = Integer.parseInt(strArray[0]);
            int lower = Integer.parseInt(strArray[1]);
            BigDecimal canGet = new BigDecimal(strArray[2]);
            int rank = Integer.parseInt(strArray[3]);

            if (rank <= upper) {
                System.out.println(0.0);
                return;
            }

            if (rank <= lower) {
                BigDecimal all = new BigDecimal(lower - upper);
                BigDecimal possibility = canGet.divide(all, 6, RoundingMode.DOWN);
                System.out.println(possibility);
                return;
            }

            System.out.println(0.0);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
