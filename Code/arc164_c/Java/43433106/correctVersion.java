import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Main().main(sc);
    }

    public void main(Scanner sc) {
        int n = sc.nextInt();
        Card[] cards = new Card[n];

        int ura = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            cards[i] = new Card(sc.nextLong(), sc.nextLong());
            if (cards[i].a < cards[i].b) {
                ans += cards[i].b;
                ura++;
            } else {
                ans += cards[i].a;
            }
        }

        if ((ura % 2 == 0 && n % 2 == 0) || (ura % 2 == 1 && n % 2 == 1)) {
            System.out.println(ans);
        } else {
            Arrays.sort(cards, (c1, c2) -> Long.compare(c1.a, c2.a));
            long ans2 = 0;
            for (int i = 0; i < n; i++) {
                long tmp = ans - Math.abs(cards[i].a - cards[i].b);
                ans2 = Math.max(tmp, ans2);
            }
            System.out.println(ans2);
        }
    }

    private static class Card {
        long a, b;

        public Card(long a, long b) {
            this.a = a;
            this.b = b;
        }
    }
}
