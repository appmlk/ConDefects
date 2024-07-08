import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        List<Long> list = new ArrayList<>();
        for (long i  = 0; i * i * i < N; i++) {
            list.add(i*i*i);
        }
        for (int i = list.size()-1; i >= 0; i--) {
            if (checkRounded(list.get(i))) {
                System.out.println(list.get(i));
                break;
            }
        }
	}

    private static boolean checkRounded(long A) {
        String S = String.valueOf(A);
        char[] arr = S.toCharArray();
        for (int i = 0; i < arr.length/2; i++) {
            if (arr[i] != arr[arr.length-i-1]) return false;
        }
        return true;
    }
}