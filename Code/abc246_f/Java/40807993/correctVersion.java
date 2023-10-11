
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static long MOD = 998244353L;
	static Set<Character>[] S ;
	static long L;
	static long sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] sp = reader.readLine().split(" ");
		int N = Integer.parseInt(sp[0]);
		L = Long.parseLong(sp[1]);
		sum = 0; 
		S = new Set[N];
		for(int i=0; i<N; i++) {
			String s = reader.readLine();
			S[i] = new HashSet<Character>();
			for(char ch : s.toCharArray()) {
				S[i].add(ch);
			}
			sum += modPow(S[i].size(),L);
			if(sum > MOD) {
				sum -= MOD;
			}
			sum(false,i,S[i]);
		}
		System.out.println(sum);
	}
	
	static void sum(boolean f,int n, Set<Character> src) {
		for(int i=0; i<n; i++) {
			Set<Character> intersection = new HashSet<Character>(src);
			intersection.retainAll(S[i]);
			if(f) {
				sum += modPow(intersection.size(),L);
				if(sum >= MOD) {
					sum -= MOD;
				}
			} else {
				sum -= modPow(intersection.size(),L);
				if(sum < 0) {
					sum += MOD;
				}
			}
			sum(!f,i,intersection);
		}
	}

    static long modPow(long base, long exponent) {
    	long result = 1;
    	long current = base;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * current) % MOD;
            }
            current = (current * current) % MOD;
            exponent >>= 1;
        }

        return result;
    }
}
