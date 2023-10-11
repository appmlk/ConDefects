import java.util.*;

public class Main {
    public static void main(String[] args) {

        IOHandler io = new IOHandler();
        String x = io.nextStr();
        io.close();
        io.output(x.matches("[0-9]{1,}00") ? "Yes" : "No");
    }


    private static class IOHandler {
        private Scanner sc = new Scanner(System.in);
        private void close() {this.sc.close();}

        private String nextStr() {return this.sc.next();}

        private <T> void output(T result) {System.out.println(result);}
    }
}