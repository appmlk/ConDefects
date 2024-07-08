// javac -d build src/*.java; java -cp build Main < in.txt > out.txt
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

record Card(Integer index,Integer a, Integer b){}

public class Main{
    public static void main(String... args){
        Scanner scanner = new Scanner(System.in);
        System.out.println(
            scanner.nextInt()<scanner.nextInt() ? "Bat":"Glove"
        );
    }

}