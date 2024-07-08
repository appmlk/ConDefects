import java.util.*;

public class Main {
	public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int i=0;i<N;i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0;j<N;j++){
                int Aij = sc.nextInt();
                if (Aij == 1) {
                    if (!sb.isEmpty()) {
                        sb.append(" ");
                    }
                    sb.append(j+1);
                }
            }
            if (!sb.isEmpty()) {
                System.out.println(sb);
            }
        }
	}
}
