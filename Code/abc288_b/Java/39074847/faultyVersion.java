import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        //Map<String, Integer> map = new TreeMap<String, Integer>();
        TreeSet<String> set = new TreeSet<String>();
        for(int i=0;i<k;i++) {
            String temp = input.next();
            set.add(temp);
        }
        int cnt = 0;
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext() && cnt < 3) {
            System.out.println(iterator.next());
            cnt++;
        }
    }
}