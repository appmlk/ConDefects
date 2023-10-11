import java.util.*;

public class Main{
    public static void main(String...args) {
        Scanner sc = new Scanner(System.in);
        final long K=sc.nextLong();
        HashMap<Long,Integer> map=PF(K);
        long min=1,max=K;
        Set<Long> keySet=map.keySet();

        while (max-min>1){
            long i=(min+max)/2;
            boolean bool=true;
            for(var l:keySet){
                int count=0;
                long w=l;
                while(i/w>=1){
                    count+=i/w;
                    w*=l;
                }
                if(map.get(l)>count){
                    bool=false;
                    break;
                }
            }
            if(bool)max=i;
            else min=i;
        }

        System.out.println(max);
    }
    public static HashMap<Long,Integer> PF(Long A) {
        int max = (int) Math.ceil(Math.pow(A,0.5))+1;
        boolean[] notPrime = new boolean[max];
        ArrayList<Integer> list=new ArrayList<>();
        for (int i = 2; i < max; i++) {
            if (!notPrime[i]) {
                list.add(i);
                for (int a=1, b=i*a; b<max;a++,b=i*a) {
                    notPrime[b] = true;
                }
            }
        }
        HashMap<Long, Integer> map = new HashMap<>();
        for(var p:list){
            int a=0;
            while (A%p==0){
                a++;
                A/=p;
            }
            if(a!=0)map.put((long)p,a);
        }
        if(A!=1){
            map.put(A,1);
        }
        return map;
    }
}
