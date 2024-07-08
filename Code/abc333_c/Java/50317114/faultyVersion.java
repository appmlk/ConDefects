import java.util.Scanner;

class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        long[] repunit=new long[12];
        for(int i=0;i<11;i++){
            repunit[i]=Long.parseLong("1".repeat(i+1));
        }

        int count=0;
        for(int i=0;i<12;i++){
            for(int j=0;j<=i;j++){
                for(int k=0;k<=j;k++){
                    count++;
                    if(count==n){
                        System.out.println(repunit[i]+repunit[j]+repunit[k]);
                    }
                }
            }
        }
    }
}
