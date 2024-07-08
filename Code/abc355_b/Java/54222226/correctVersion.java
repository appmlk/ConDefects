import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Element {
        int value;
        char source; // 'A' or 'B'

        Element(int value, char source) {
            this.value = value;
            this.source = source;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        List<Element> numbers = new ArrayList<>();

        for(int i = 0; i < a; i++) numbers.add(new Element(sc.nextInt(), 'A'));
        for(int i = 0; i < b; i++) numbers.add(new Element(sc.nextInt(), 'B'));

        numbers = numbers.stream().sorted(Comparator.comparingInt(e -> e.value)).toList();

        boolean twoConsecutive = false;
        int counter = 1;

        while(!twoConsecutive && counter < numbers.size()){

            if(numbers.get(counter - 1).source == 'A' &&  numbers.get(counter).source == 'A') twoConsecutive = true;
            counter++;
        }

        System.out.println(twoConsecutive ? "Yes" : "No");
    }

}
