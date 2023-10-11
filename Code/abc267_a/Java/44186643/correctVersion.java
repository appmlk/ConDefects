import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        String S = scan.next();
        Map<String, Integer> map = new HashMap<>();
        map.put("Monday", 5);
        map.put("Tuesday", 4);
        map.put("Wednesday", 3);
        map.put("Thursday", 2);
        map.put("Friday", 1);
        
        System.out.println(map.get(S));
    }
}