import java.util.*;

public class Main {
    public static void main(String[] args) {
        try(Scanner scan = new Scanner(System.in)) {
            int N = scan.nextInt();
            int Q = scan.nextInt();

            //int[]x = new int[Q+10000000];
            //int[]a = new int[N+10000000];
            //int[]p = new int[N+10000000];

            ArrayList<Integer> po = new ArrayList<>();
            ArrayList<Integer> va = new ArrayList<>();
            po.add(0);
            va.add(0);

            for (int i = 1; i <= N; i++) {
                po.add(i);
                va.add(i);
            }
            for (int i = 1; i <= Q; i++) {
                int z = scan.nextInt();
                int point = po.get(z);
                int point_value = va.get(point);
                int next_point = (point != N ? point + 1 : point - 1);
                int next_value = va.get(next_point);
                Collections.swap(va, point, next_point);
                Collections.swap(po, point_value, next_value);
            }

            for (int i = 1; i <= N; i++) {
                System.out.print(va.get(i) + " ");

            }


//            for(int i = 1;i<=N;i++) {
//                a[i] = i ;p[i] = i;
//
//            }
//
//            for(int i =0;i<Q;i++){
//                int z = scan.nextInt();
//                int tonari =(a[z]!=N?a[z]+1:a[z]-1);
//                int tonari_value = p[tonari];
//                int c = a[tonari_value];
//                int l = a[z];
//                a[tonari_value] = a[z];
//                a[z] = c;
//                p[tonari] = p[l];
//                p[l] = tonari_value;
//                //for(int k = 1;k<=N;k++)System.out.print(p[k]+" ");
//               // System.out.println();
//
//
//
//
//
////            for (int i = 1;i<=Q;i++)
////                x[i] = scan.nextInt();
//
////            for(int i =1;i<=Q;i++) {
////
////                int z = scan.nextInt();
////                int tmp = p[z];//値の現在地
////                //int y = (tmp == N ? tmp - 1 : tmp + 1);
////                int y =tmp;
////                    if(tmp!=N)y++; //交換するべき値の現在地
////                    else y--;
////                int next = a[y];//隣の値
////                p[next] = tmp;
////                p[z] = y;
////                a[tmp] = next;
////                a[y] = z;
//
//
////
////                int z = x[i];
////
////                    int tmp = a[z];//指定した値の現在地
////                    int y =tmp;
////                    if(tmp!=N)y++; //交換するべき値の現在地
////                    else y--;
////                int next = p[y];
////                a[next] = tmp;
////                a[z] = y;
////                p[tmp] = next;
////                p[y] = z;
//
////                    int v0 = p[tmp];
////                    int v1 = p[y];
//                //System.out.println(v0+" "+v1);
////
////                    int v = a[v0];//左隣の現在地
////                    int vv = a[v1];
////                    a[v0] = vv;
////                    a[v1] = v;
////                //System.out.println(a[v0]+" "+a[v1]);
////
////                    int b = p[tmp];//左隣の現在地
////                    int bb = p[y];
////                    p[tmp] = bb;
////                    p[y] = b;
//                //System.out.println(p[tmp]+" "+p[y]);
//
////                for(int k = 0;k<N;k++)System.out.print(a[k]+1+" ");
////                System.out.println();
//
//
//
//
//            }

//            int z = scan.nextInt();
//            if (a[z] <N) {
//                int tmp = p[z];//指定した値の現在地
//                int c = p[tmp + 1];//指定した値の現在地の右隣の値
//                p[tmp] = c;
//                p[tmp + 1] = z;//値を交換
//                int y = a[c];//右隣の現在地
//                a[c] = a[z];
//                a[z] = y;//現在地を交換
//            } else {
//                int tmp = a[z];//指定した値の現在地
//                int c = p[tmp - 1];//指定した値の現在地の左隣の値
//                p[tmp] = c;
//                p[tmp - 1] = z;//値を交換
//                int y = a[c];//左隣の現在地
//                a[c] = a[z];
//                a[z] = y;//現在地を交換
//            }

//                for(int k = 0;k<N;k++)System.out.print(a[k+1]);
//               System.out.println();

//                if(x[i]+1<=N){
//                    int tmp =a[x[i]-1];
//                    a[x[i]-1] = a[x[i]];
//                    a[x[i]] = tmp;
//                }else{
//                    int tmp =a[x[i]-1];
//                    a[x[i]-1] = a[0];
//                    a[0] = tmp;
//
//                }
//                for(int A:a)System.out.print(A);
//                System.out.println();
//            for(int k = 1;k<=N;k++)System.out.print(p[k]+" ");
//            }


//            for(int k = 0;k<N;k++){
//                System.out.print(a[p[k]]);
//            }System.out.println();
            //System.out.print(A+' ');


        }


    }
}
