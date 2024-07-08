import java.util.Scanner; 

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[M];
        int[][] X = new int[N][M];
        int[] Check =new int[M];
        //input set
        for (int i=0;i<M;i++){
            A[i]=sc.nextInt();
        }
        //inupt set
        for (int i=0;i<N;i++){ 
           for (int j=0;j<M;j++){
           X[i][j]=sc.nextInt();  
           }
        }
        
        for (int j=0;j<M;j++){
            int sum=0;
            for (int i=0;i<N;i++){
                sum+=X[i][j];
            }
            Check[j]=sum;
            
        }

        for(int i=0;i<M;i++){
            
            if (Check[i]<A[i]){
               
               System.out.println("No");
               System.exit(0);
            }
        }
        System.out.println("Yes");
       
    }
    }