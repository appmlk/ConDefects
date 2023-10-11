import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        List<Integer> A= new ArrayList<>(), B = new ArrayList<>();
        //List<Float> T = new ArrayList<>();
        float sum = 0, x = 0;
        for(int i = 0; i < N; i++){
            A.add(scan.nextInt());
            B.add(scan.nextInt());
            sum += (float)A.get(i)/B.get(i);
        }
        
        sum = sum/2;
        System.out.println(sum);
        for(int i = 0; sum > 0; i++){
            if(sum < (float)A.get(i)/B.get(i)){
                System.out.println(sum);
                x += B.get(i)*sum;
                sum = 0;
            }else{
                x += A.get(i);
                sum-= (float)A.get(i)/B.get(i);
            }
        }
        System.out.println(x);
    }
}
