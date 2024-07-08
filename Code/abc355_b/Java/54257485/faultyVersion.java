import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            ArrayList<Integer> listA = new ArrayList<>();
            ArrayList<Integer> listB = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                listA.add(sc.nextInt());
            }

                for (int i = 0; i < M; i++) {
                    listB.add(sc.nextInt());
                }

                ArrayList<Integer> combinedList = new ArrayList<>(listA);
                combinedList.addAll(listB);

                Collections.sort(combinedList);

                for (int i = 0; i < combinedList.size() - 1; i++) {
                    if (listA.contains(combinedList.get(i)) && listA.contains(combinedList.get(i + 1))) {
                        System.out.println("Yes");
                        break;
                    }
                }

                System.out.println("No");
            }
        }
    }
