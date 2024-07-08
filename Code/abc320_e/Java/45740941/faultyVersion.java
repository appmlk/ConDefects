import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] ret = new long[n + 1];
        long time = 1;
        PriorityQueue<Person> row = new PriorityQueue<>((a, b) -> {
            return a.id - b.id;
        });
        PriorityQueue<Person> out = new PriorityQueue<>((a, b) -> {
            if (a.time == b.time) {
                return 0;
            } else if (a.time < b.time) {
                return -1;
            }
            return 1;
        });
        for (int i = 0; i < n; ++i) {
            out.offer(new Person(i, 1));
        }
        int [][] q = new int [m][3];
        for (int i = 0;  i < m ; ++i) {
            q[i][0] = sc.nextInt();
            q[i][1] = sc.nextInt();
            q[i][2] = sc.nextInt();
        }
        Arrays.sort(q, (a, b) -> {
            return a[0] - b[0];
        });
        for (int i = 0; i < m; ++i) {
            int t = q[i][0];
            int w = q[i][1];
            int s = q[i][2];
            while (!out.isEmpty() && t >= out.peek().time) {
                row.offer(out.poll());
            }
            if (!row.isEmpty()) {
                Person p = row.poll();
                int id = p.id;
                ret[id] += w;
                p.time += s;
                out.offer(p);
            }         
        }
        for (int i = 0; i < n; ++i) {
            System.out.println(ret[i]);
        }
    }

    static class Person {
        int id;
        long time;

        Person(int id, long time) {
            this.id = id;
            this.time = time;
        }
    }

}