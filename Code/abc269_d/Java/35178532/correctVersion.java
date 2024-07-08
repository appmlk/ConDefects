import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Map<Integer, Map<Integer, Boolean>> grid = new HashMap<>();

    static int N;

    static int usedCount = 0;

    static int connect = 0;

    public static void main( String[] args) {
        Scanner scn = new Scanner( System.in);
        N = scn.nextInt();
        int ans = 0;
        for ( int i = 0; i < N; i++) {
            int x = scn.nextInt();
            int y = scn.nextInt();
            if ( grid.containsKey( x)) {
                grid.get( x).put( y, false);
            }
            else {
                Map<Integer, Boolean> subGrid = new HashMap<>();
                subGrid.put( y, false);
                grid.put( x, subGrid);
            }
        }
        for ( int x : grid.keySet()) {
            for ( int y : grid.get( x).keySet()) {
                ans += recursion( x, y);
            }
        }
        System.out.println( ans);
    }

    public static int recursion( int x, int y) {
        if ( usedCount < N && !grid.get( x).get( y)) {
            if ( grid.get( x) != null) {
                if ( grid.get( x).get( y - 1) != null && !grid.get( x).get( y - 1)) {
                    usedCount++;
                    connect++;
                    grid.get( x).put( y, true);
                    recursion( x, y - 1);
                    grid.get( x).put( y - 1, true);
                }
                if ( grid.get( x).get( y + 1) != null && !grid.get( x).get( y + 1)) {
                    usedCount++;
                    connect++;
                    grid.get( x).put( y, true);
                    recursion( x, y + 1);
                    grid.get( x).put( y + 1, true);
                }
            }
            if ( grid.get( x - 1) != null) {
                if ( grid.keySet().contains( x - 1)) {
                    if ( grid.get( x - 1).get( y) != null && !grid.get( x - 1).get( y)) {
                        usedCount++;
                        connect++;
                        grid.get( x).put( y, true);
                        recursion( x - 1, y);
                        grid.get( x - 1).put( y, true);

                    }
                    if ( grid.get( x - 1).get( y - 1) != null && !grid.get( x - 1).get( y - 1)) {
                        usedCount++;
                        connect++;
                        grid.get( x).put( y, true);
                        recursion( x - 1, y - 1);
                        grid.get( x - 1).put( y - 1, true);
                    }
                }
            }
            if ( grid.get( x + 1) != null) {
                if ( grid.keySet().contains( x + 1)) {
                    if ( grid.get( x + 1).get( y + 1) != null && !grid.get( x + 1).get( y + 1)) {
                        usedCount++;
                        connect++;
                        grid.get( x).put( y, true);
                        recursion( x + 1, y + 1);
                        grid.get( x + 1).put( y + 1, true);
                    }
                    if ( grid.get( x + 1).get( y) != null && !grid.get( x + 1).get( y)) {
                        usedCount++;
                        connect++;
                        grid.get( x).put( y, true);
                        recursion( x + 1, y);
                        grid.get( x + 1).put( y, true);
                    }
                }
            }
            return 1;
        }
        else {
            return 0;
        }
    }
}
