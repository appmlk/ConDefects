import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tuzuki = Integer.parseInt(sc.next());
        int kati = Integer.parseInt(sc.next());
        String takaoka = sc.next();
        int nakajima = 0;

        char[] takaoka1 = takaoka.toCharArray();

        for (int i=0;i<tuzuki;++i){
            if (nakajima <= kati)++nakajima;
            if (takaoka1[i] == 'o' && nakajima > kati){
                takaoka1[i] = 'x';
            }
        }
        takaoka = String.valueOf(takaoka1);
        System.out.println(takaoka);
        sc.close();

    }
}