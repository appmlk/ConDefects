import java.util.*;
public class Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Integer> Q = new ArrayList<>();
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        for(int i=0;i<N;i++) Q.add(sc.nextInt());
        for(int i=0;i<N;i++) A.add(sc.nextInt());
        for(int i=0;i<N;i++) B.add(sc.nextInt());

        //料理Aのmax数算出
        int A_MAX_NUM = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            if(A.get(i)==0) continue;
            A_MAX_NUM = Integer.min(A_MAX_NUM,Q.get(i)/A.get(i));
        }
        if(A_MAX_NUM==Integer.MAX_VALUE) A_MAX_NUM = 0;

        int MAX_NUM = 0;
        for(int i=1;i<=A_MAX_NUM;i++){
            int B_MAX_NUM = Integer.MAX_VALUE;
            for(int j=0;j<N;j++){
                if(B.get(j)==0) continue;
                B_MAX_NUM = Integer.min(B_MAX_NUM,(Q.get(j)-A.get(j)*i)/B.get(j));
            }
            if(B_MAX_NUM==Integer.MAX_VALUE) B_MAX_NUM = 0;
            MAX_NUM = Integer.max(MAX_NUM,(i+B_MAX_NUM));
        }
        System.out.println(MAX_NUM);
    }
}
