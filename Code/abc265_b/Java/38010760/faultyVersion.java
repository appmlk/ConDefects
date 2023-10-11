import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        int N = scan.nextInt();
        int M = scan.nextInt();
        int T = scan.nextInt();

        int[] A = new int[N];
        for(int i = 0; i < N - 1; i++){
          A[i] = scan.nextInt();
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0; i < M; i++){
          int k = scan.nextInt();
          int v = scan.nextInt();

          if(map.containsKey(k)){
            map.put(k, map.get(k) + v);
          }else{
            map.put(k, v);
          }
        }

        for(int i = 0; i < N - 1; i++){
          T -= A[i];
          if(T <= 0){
            System.out.println("No");
            return;
          }
          if(map.containsKey(i + 2)){
            T += map.get(i + 2);
          }
        }

        System.out.println("Yes");

    }
}