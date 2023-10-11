    import java.util.*;
     
    public class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt(), k = sc.nextInt();
            int ans = n;
            String s = sc.next();
            char[] str = s.toCharArray();
            //枚举循环节的长度
            for(int i = 1; i <= n; i++){
                //需要修改的次数累计变量
                int cnt = 0;
                //除不尽肯定不能当循环节的长度
                if(n % i != 0){
                    continue;
                }
                //遍历第一组循环节
                for(int j = 0; j < i; j++){
                    int[] cs = new int[26];
                    for(int m = 0; j + i * m < s.length(); m++){
                        cs[str[j + i * m] - 'a']++;
                    }
                    int sum = 0;
                    for(int num : cs){
                        sum += num;
                    }
                    sum -= Arrays.stream(cs).max().getAsInt();
                    cnt += sum;
                }
                if(cnt <= k){
                    ans = Math.min(ans, i);
                }
            }
            System.out.println(ans);
        }
    }