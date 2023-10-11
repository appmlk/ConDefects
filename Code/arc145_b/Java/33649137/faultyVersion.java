import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long a = sc.nextLong();
        long b = sc.nextLong();
        sc.close();

        if(n<a){
            System.out.println(0);
            return;
        }

        if(a <= b){
            System.out.println(n);
            return;
        }

        // if(n<b){
        //     if(n>=a) System.out.println(n-a+1);
        //     else System.out.println(0);
        //     return;
        // }
        
        // if(a == 1){
        //     System.out.println(n);
        //     return;
        // }
        // if(b==1){
        //     System.out.println(n/a);
        //     return;
        // }



        // long w = n/a;
        // long ans = w*b;
 
        // if(n< a*w + b-1){
        //     ans = b*(w-1) + (n-a*w + 1);
        // }

        // System.out.println(ans);

        System.out.println((n/a - 1)*b + 1 + Math.min(n%a, b - 1));
    }
}


// System.out.println();

// for(int i=0; i<n; i++){

// }



// for(int i=0; i<n; i++){
//     for(int j=0; j<n; j++){

//     }
// }

// ArrayList<Integer> l = new ArrayList<>();
// LinkedList<Integer> l = new LinkedList<>();


// for(Map.Entry<String, String> entry : map.entrySet()) {
//     System.out.println(entry.getKey());
//     System.out.println(entry.getValue());
// }

// TreeMap<Integer,Integer> m = new TreeMap<>();





