import java.util.*;

public class Main {
    static ArrayList<Cheese> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), w = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list.add(new Cheese(a,b));
        }
        long count = 0;
        Collections.sort(list, (c1,c2) -> (c2.a - c1.a));
        for (Cheese c : list) {
            int take = Math.min(w,c.b);
            count += (long)take * c.a;
            w-=take;
        }
        System.out.println(count);
    }
}
class Cheese {
    int a,b;
    public Cheese(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public String toString(){
        return a + " " + b;
    }
}

