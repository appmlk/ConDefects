import java.util.*;




public class Main {
    public static void main(String[] args) {
        
    
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] S= new String[N];
        int[] T= new int[N];
        HashSet<String> set = new HashSet<>();
        int best = -1;
        int bestscore = -1;
        for(int i = 0; i < N;i++){
            S[i] = sc.next();
            T[i] = sc.nextInt();
        }

        for(int  i = 0; i<N;i++){
            if(!set.contains(S[i])){
                if(T[i] > bestscore){
                    best = i;
                    bestscore = T[i];
                    set.add(S[i]);
                }
            }
        }
        System.out.println(best+1);
    }

}