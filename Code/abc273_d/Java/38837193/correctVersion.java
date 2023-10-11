import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int rs = sc.nextInt();
        int cs = sc.nextInt();
        int n = sc.nextInt();
        Map<Integer, TreeSet<Integer>> rowWall = new HashMap<>();
        Map<Integer, TreeSet<Integer>> colWall = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            if (!rowWall.containsKey(r)) {
                rowWall.put(r, new TreeSet<>());
                rowWall.get(r).add(0);
                rowWall.get(r).add(w+1);
            }
            if (!colWall.containsKey(c)) {
                colWall.put(c, new TreeSet<>());
                colWall.get(c).add(0);
                colWall.get(c).add(h+1);
            }
            rowWall.get(r).add(c);
            colWall.get(c).add(r);
        }

        StringBuilder sb = new StringBuilder();
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            String d = sc.next();
            int l = sc.nextInt();
            if (d.equals("L")) {
                int wall = 0;
                if (rowWall.containsKey(rs)) wall = rowWall.get(rs).lower(cs);
                cs = Math.max(wall+1, cs-l);
            } else if (d.equals("R")) {
                int wall = w+1;
                if (rowWall.containsKey(rs)) wall = rowWall.get(rs).higher(cs);
                cs = Math.min(wall-1, cs+l);
            } else if (d.equals("U")) {
                int wall = 0;
                if (colWall.containsKey(cs)) wall = colWall.get(cs).lower(rs);
                rs = Math.max(wall+1, rs-l);
            } else if (d.equals("D")) {
                int wall = h+1;
                if (colWall.containsKey(cs)) wall = colWall.get(cs).higher(rs);
                rs = Math.min(wall-1, rs+l);
            }
            sb.append(rs + " " + cs + "\n");
        }
        System.out.println(sb);

    }
}

