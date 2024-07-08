import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] n = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    for (int i = n[0]; i <= n[2]; i += n[1]) {
      System.out.print(i + " ");
    }
    br.close();
  }

}
