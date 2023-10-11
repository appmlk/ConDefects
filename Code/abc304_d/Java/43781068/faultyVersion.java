import java.awt.*;
import java.util.*;

public class Main{

    public static void main(String...args) {
        Scanner sc = new Scanner(System.in);
        final int W = sc.nextInt(), H = sc.nextInt(), N = sc.nextInt();
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new Point(sc.nextInt(),sc.nextInt()));
        }
        final int A=sc.nextInt();
        ArrayList<Integer> xList=new ArrayList<>();
        for(int i=0;i<A;i++){
            xList.add(sc.nextInt());
        }
        xList.add(W);
        final int B=sc.nextInt();
        ArrayList<Integer> yList=new ArrayList<>();
        for(int i=0;i<B;i++){
            yList.add(sc.nextInt());
        }
        yList.add(H);
        HashMap<Point,Integer> map=new HashMap<>();
        HashSet<Point> set = new HashSet<>();
        for(var p:list) {
            int x = index(xList, p.x);
            int y = index(yList, p.y);
            Point point = new Point(x, y);
            if (set.add(point)) {
                map.put(point, 1);
            } else {
                map.put(point, map.get(point) + 1);
            }
        }
        Iterator<Integer> ite = map.values().iterator();
        int min=100100100,max=0;
        while (ite.hasNext()){
            int i=ite.next();
            min=Math.min(min,i);
            max=Math.max(max,i);
        }

        if((long)(A+1)*(B+1)>N)min=0;

        System.out.println(min+" "+max);
    }
    private static int index(ArrayList<Integer> list,int i){
        int[] range=new int[]{-1, list.size()};
        while(range[1]-range[0]>1){
            int index=(range[1]+range[0])/2;
            if(list.get(index)<i){
                range[0]=index;
            }else if(i<list.get(index)){
                range[1]=index;
            }
        }
        return range[1];
    }
}
