import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        int N = scan.nextInt();
        List<Integer> list = new ArrayList<>();

        for(int i = 2; i <= 2 * N + 1; i++){
            list.add(i);
        }

        System.out.println(1);
        System.out.flush();
        for(int i = 0; i < N; i++){
            int aoki = scan.nextInt();
            list.remove(list.indexOf(aoki));
            System.out.println(list.get(0));
            System.out.flush();
            list.remove(0);
        }
        scan.nextInt();
        return;
    }
}