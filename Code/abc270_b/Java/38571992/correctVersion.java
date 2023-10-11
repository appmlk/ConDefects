import java.util.Scanner;
import java.lang.Math;
public class Main
{
    int classa() {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        int dist = 0;
        if (y < 0 && x > 0) {
            dist = x;
            return dist;
        } else if (y > 0 && x < 0) {
            dist = Math.abs(x);
            return dist;
        }
        else if (y>0 && x >0)
        {
            if (x < y)
            {
                dist = x;
                return dist;
            }
            else if (x > y && z < y && z < 0)
            {
                dist = Math.abs(z)*2 + x;
                return dist;
            } else if (x > y && z < y && z > 0)
            {
                dist = x;
                return dist;

            } else {
                dist = -1;
                return dist;
            }
        }
        else if(y<0 && x < 0)
        {
            if (x > y)
            {
                dist = Math.abs(x);
                return dist;
            }
            else if (x < y && z > y && z > 0)
            {
                dist = z*2 + Math.abs(x);
                return dist;
            }
            else if (x < y && z > y && z < 0)
            {
                dist = Math.abs(x);
                return dist;
            }
            else
            {
                dist = -1;
                return dist;
            }
        }
        else
        {
            dist = -1;
            return dist;
        }
    }
        public static void main (String[]args){
            System.out.println(new Main().classa());
        }
    }