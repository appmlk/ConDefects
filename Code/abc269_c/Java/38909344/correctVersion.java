import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        ArrayList<Long> list = new ArrayList<>();
        for(int mask = 0; mask < 60; mask++) {
            if(((n >> mask) & 1) >= 1 ) list.add((long)mask);
        }
        long max_mask = 1 << list.size();
        for(int mask = 0; mask < max_mask; mask++){
            long res = 0;
            for(int i = 0; i < list.size(); i++){
                if((mask & (1 << i)) > 0) res += 1L << list.get(i);
            }
            System.out.println(res);
        }

        }
    }

