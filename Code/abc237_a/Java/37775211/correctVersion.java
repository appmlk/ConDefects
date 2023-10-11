import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
class Main {
    private static void solve(long n)
    {
        long limit=(long)Math.pow(2, 31);
        if(n>=(-1*limit) && n<=(limit-1))
        {
            System.out.println("Yes");
        }else
        {
            System.out.println("No");
        }

    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        long n=scn.nextLong();
        solve(n);
    }
}
