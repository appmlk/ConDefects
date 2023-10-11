import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    
        var sc = new Scanner(System.in);
        
        int n = Integer.parseInt(sc.next());
        int k = Integer.parseInt(sc.next());
        char[] s = sc.next().toCharArray();
        
        int x = 0;
        for(int i = 0; i < n; i++){
            if(s[i] == 'X'){
                x++;
            }
        }
        
        if(x == n){
            System.out.println(Math.max(k-1, 0));
        }else if(k == 0 || k == n){
            if(k == n){
                for(int i = 0; i < n; i++){
                    if(s[i] == 'X'){
                        s[i] = 'Y';
                    }else{
                        s[i] = 'X';
                    }
                }
            }
            int ans = 0;
            for(int i = 0; i < n-1; i++){
                if(s[i] == 'Y' && s[i+1] == 'Y'){
                    ans++;
                }
            }
            System.out.println(ans);
        }else if(k == x){
            System.out.println(n-1);
        }else{
            if(k > x){
                k = n-k;
                for(int i = 0; i < n; i++){
                    if(s[i] == 'X'){
                        s[i] = 'Y';
                    }else{
                        s[i] = 'X';
                    }
                }
            }
            
            int ans = 0;
            for(int i = 0; i < n-1; i++){
                if(s[i] == 'Y' && s[i+1] == 'Y'){
                    ans++;
                }
            }
            
            int left = 0;
            while(left < n && s[left] == 'X'){
                left++;
            }
            while(left < n && s[left] == 'Y'){
                left++;
            }
            
            int right = n-1;
            while(0 <= right && s[right] == 'X'){
                right--;
            }
            while(0 <= right && s[right] == 'Y'){
                right--;
            }
            
            var pq = new PriorityQueue<Integer>();
            pq.add(Integer.MAX_VALUE);
            int count = 0;
            for(int i = left; i <= right; i++){
                if(s[i] == 'X'){
                    count++;
                }
                if(s[i] == 'Y' || i == right){
                    if(count >= 1){
                        pq.add(count);
                        count = 0;
                    }
                }
            }
            
            int countX = 0;
            for(int i = 0; i < k; i++){
                if(countX == 0){
                    countX = pq.poll();
                }
                countX--;
                ans++;
                if(countX == 0){
                    ans++;
                }
            }
            System.out.println(ans);
        }
    }
}