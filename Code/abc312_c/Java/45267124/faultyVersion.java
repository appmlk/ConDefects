import java.util.*;
import java.util.function.Function;

public class Main{
    public static void main(String...args) {
        Scanner sc = new Scanner(System.in);
        final int N=sc.nextInt(),M=sc.nextInt();
        ArrayList<Integer> A=new ArrayList<>(),B=new ArrayList<>();
        for(int i=0;i<N;i++)A.add(sc.nextInt());
        for(int i=0;i<M;i++)B.add(sc.nextInt());
        A.sort(Comparator.naturalOrder());
        B.sort(Comparator.reverseOrder());

        Function<Integer,Integer> sell=i->{
            if(A.get(N-1)<=i)return N;
            else if(i<A.get(0))return 0;

            int min=0,max=N-1;
            while(max-min>1){
                int index=(max+min)/2;
                if(A.get(index)<=i){
                    min=index;
                }else {
                    max=index;
                }
            }
            return min+1;
        };

        Function<Integer,Integer> buy=i->{
            if(i<=B.get(M-1))return M;
            else if(B.get(0)<i)return 0;

            int min=0,max=M-1;
            while (max-min>1){
                int index=(max+min)/2;
                if(B.get(index)>=i){
                    min=index;
                }else {
                    max=index;
                }
            }
            return min+1;
        };
        int min=0,max=A.get(N-1);
        while (max-min>1){
            int a=(max+min)/2;
            int seller=sell.apply(a);
            int buyer= buy.apply(a);
            if(seller>=buyer){
                max=a;
            }else {
                min=a;
            }
        }
        System.out.println(max);
    }
}
