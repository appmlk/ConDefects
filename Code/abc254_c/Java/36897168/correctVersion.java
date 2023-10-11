import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main( String[] args) {
        Scanner scn = new Scanner( System.in);
        int N = scn.nextInt();
        int K = scn.nextInt();
        int[] array = new int[N];
        for ( int i = 0; i < N; i++) {
            array[i] = scn.nextInt();
        }
        int[] brray = Arrays.copyOf( array, N);
        Arrays.sort( brray);
        List<Set<Integer>> listA = new ArrayList<>();
        List<Set<Integer>> listB = new ArrayList<>();
        if ( K == 1) {
            System.out.println( "Yes");
            return;
        }
        else if ( N / 2 >= K) {
            for ( int h = 0; h < K; h++) {
                listA.add( new TreeSet<>());
                listB.add( new TreeSet<>());
            }
            for ( int i = 0; i < N; i++) {
                listA.get( i % K).add( array[i]);
                listB.get( i % K).add( brray[i]);
            }
            for ( int l = 0; l < K; l++) {
                if ( !listA.get( l).equals( listB.get( l))) {
                    System.out.println( "No");
                    return;
                }
            }
            System.out.println( "Yes");
        }
        else {
            List<Integer> arrayA = new ArrayList<>();
            List<Integer> arrayB = new ArrayList<>();
            for ( int h = 0; h < N - K; h++) {
                listA.add( new TreeSet<>());
                listB.add( new TreeSet<>());
            }
            for ( int i = 0; i < N; i++) {
                if ( N - i > K || K <= i) {
                    listA.get( i % K).add( array[i]);
                    listB.get( i % K).add( brray[i]);
                }
                else {
                    arrayA.add( array[i]);
                    arrayB.add( brray[i]);
                }

            }
            if ( !arrayA.equals( arrayB)) {
                System.out.println( "No");
                return;
            }
            for ( int l = 0; l < N - K; l++) {
                if ( !listA.get( l).equals( listB.get( l))) {
                    System.out.println( "No");
                    return;
                }
            }
            System.out.println( "Yes");
        }
    }
}