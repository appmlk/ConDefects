
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;


public class Main {
//class Codechef {

    static FastReader sc;
    static StringBuilder sb = new StringBuilder();
    static int mod = (int) (Math.pow(10, 9) + 7);
    static final int dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0, 1};
    static final int[] dx8 = {-1, -1, -1, 0, 0, 1, 1, 1}, dy8 = {-1, 0, 1, -1, 1, -1, 0, 1};
    static final int[] dx9 = {-1, -1, -1, 0, 0, 0, 1, 1, 1}, dy9 = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static HashSet<Character> set;
    static final double eps = 1e-10;
    static long[] arr = new long[100001];
    static List<Integer> primeNumbers = new ArrayList<>();
    static int cnt = 0;
    static HashSet<Character> hor = new HashSet<>();
    static HashSet<Character> ver = new HashSet<>();

    public static void main(String[] args) {
        sc = new FastReader();
        int testCases = 1;
        hor.add('L');
        hor.add('R');
        ver.add('U');
        ver.add('D');
        //primeSieve(40000000);
        //testCases = sc.nextInt();
        while (testCases-- > 0) compute();
        //sb.append(cnt);
        System.out.println(sb.toString());
    }

    public static void compute() {
        //Arrays.sort(al,Comparator.comparingInt(ar -> ar.value));
        //Arrays.sort(al,Comparator.comparingInt(ar -> ar.index));

        //shortestCompletingWord()


        //

        String s="1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
        int n=sc.nextInt();
        sb.append("3.").append("\n");
        for(int i=0;i<n;i++){
            sb.append(s.charAt(i));
        }

            



                /*res.append(s.charAt(i));
                if(i<l-2 && check(s.substring(i,i+3)))
                   i+=2;*/
        //sb.append(((risk^x)&risk)>0?(-1):(risk^x)).append("\n");
        //sb.append(isValid(x,y,z)).append("\n");
        // int sec = Arrays.stream(arr).sorted().distinct().skip(1).findFirst().getAsInt();
    }

    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        return ((int) Arrays.stream(hours).filter(i -> i >= target).count());
    }
    public boolean isSame(String a,String b){
        int l1 = a.length(),l2=b.length();
        if(l1!=l2) return false;
        for(int i=0;i<l1;i++){
            if(a.charAt(i)!=b.charAt(l1-i-1))
                return false;
        }
        return true;
    }

    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (delayedTime+arrivalTime)%24;
    }
    public String makeSmallestPalindrome(String s) {
        StringBuilder res = new StringBuilder();
        int l=s.length();
        for(int i=0;i<=l/2;i++){
            if(s.charAt(i)==s.charAt(l-1-i))
                res.append(s.charAt(i));
            else
                res.append(s.charAt(i)<s.charAt(l-1-i)?s.charAt(i):s.charAt(l-i-1));
        }
        if(l%2==0)
            return res.toString()+res.reverse().toString();
        return res.toString()+s.charAt(l/2)+res.reverse().toString();
    }

    public int minimizedStringLength(String s) {
        int[] arr = new int[26];
        for (char c : s.toCharArray())
            arr[c - 'a']++;
        int res = 0;
        for (int i : arr) {
            if (i > 0)
                res++;
        }
        return res;
    }

    private static int findsum(int n) {
        int res = 0;
        while (n > 0) {
            res++;
            n /= 10;
        }
        return res;
    }

    public static boolean check(char a, char b) {
        if (a == '0' || a == 'o') {
            if (b != '0' && b != 'o')
                return false;
            return true;
        }
        if (a == '1' || a == 'l') {
            if (b != '1' && b != 'l')
                return false;
            return true;
        }
        return false;
    }

    public String removeTrailingZeros(String num) {
        int l = num.length();
        while (num.charAt(l--) == '0') ;
        return num.substring(0, l);
    }

    public int buyChoco(int[] prices, int money) {
        int sum = Arrays.stream(prices).sorted().limit(2).sum();
        return money >= sum ? money - sum : money;
    }

    public static long nChooseK(int n, int k) {
        if (k > n) {
            return 0; // invalid input
        }

        long numerator = 1L;
        for (int i = 1; i <= k; i++) {
            numerator *= (n - (k - i));
            numerator /= i;
        }
        return numerator;
    }

    public int maximizeSum(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        max *= 2;
        max += (k - 1);
        max *= k;
        max /= 2;
        return max;
    }

    public static boolean check(String s) {
        //System.out.println(s);
        if (s.equalsIgnoreCase("and") || s.equalsIgnoreCase("not") || s.equalsIgnoreCase("that") || s.equalsIgnoreCase("the") || s.equalsIgnoreCase("you"))
            return true;
        return false;
    }

    public int minNumber(int[] nums1, int[] nums2) {
        int min1 = Arrays.stream(nums1).min().getAsInt();
        int min2 = Arrays.stream(nums1).min().getAsInt();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i : nums1) {
            for (int ii : nums2) {
                if (i == ii)
                    return i;
            }
        }
        int m1 = Math.min(min1, min2);
        int m2 = min2 + min1 - m1;
        return 10 * m1 + m2;
    }

    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int res = 0;
        if (k <= numOnes) return Math.min(k, numOnes);
        else {
            res += numOnes;
            k -= numOnes;
        }
        if (k <= numZeros) return res;
        else
            k -= numZeros;
        if (k <= numNegOnes) return res - numNegOnes;
        return -1 * (numNegOnes - res);
    }

    public int passThePillow(int n, int time) {
        int q = time / n;
        if (q % 2 == 0)
            return time % n;
        return n - time % n;
    }

    public static boolean match(Character c, Character d) {

        if (ver.contains(c) && ver.contains(d)) return true;
        if (hor.contains(c) && hor.contains(d)) return true;
        return false;
    }

    public int[] evenOddBit(int n) {
        int[] res = new int[2];
        int odd = 0, even = 0;
        String s = Integer.toBinaryString(n);
        System.out.println(s);
        int l = s.length(), t = 0;
        for (int i = l - 1; i >= 0; i--) {
            System.out.println(s.charAt(i));
            if (s.charAt(i) == 1) {
                if (t % 2 == 0)
                    even++;
                else
                    odd++;
                t++;
            }
        }
        res[0] = even;
        res[1] = odd;
        return res;
    }

    public int maxScore(int[] nums) {
        long res = 0;
        int rr = 0;
        ArrayList<Integer> negs = new ArrayList<>();
        for (int i : nums) {
            if (i >= 0) {
                rr++;
                res += i;
            } else
                negs.add(i);
        }
        Collections.sort(negs, Collections.reverseOrder());
        for (int i : negs) {
            res += i;
            if (res > 0)
                rr++;
            else
                break;
        }
        return rr;
    }

    public int vowelStrings(String[] words, int left, int right) {
        int res = 0;
        for (int i = left; i <= right; i++) {
            if (isVowel(words[i].charAt(0)) && isVowel(words[i].charAt(words[i].length() - 1)))
                res++;
        }
        return res;
    }

    static int nCr(int n, int r) {
        return fact(n) / (fact(r) *
                fact(n - r));
    }

    // Returns factorial of n
    static int fact(int n) {
        if (n == 0)
            return 1;
        int res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

    public int splitNum(int num) {
        StringBuilder n1 = new StringBuilder();
        StringBuilder n2 = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 10);
            num /= 10;
        }
        Collections.sort(list, Collections.reverseOrder());
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0)
                n1.append(list.get(i));
            else
                n2.append(list.get(i));
        }
        System.out.println(n1.toString() + " " + n2.toString());
        return Integer.parseInt(n1.toString()) + Integer.parseInt(n2.toString());

    }

    public int minMaxDifference(int num) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 10; i++) {
            min = Math.min(min, Integer.parseInt(String.valueOf(num).replaceAll(Integer.toString(i), "0")));
            max = Math.max(max, Integer.parseInt(String.valueOf(num).replaceAll(Integer.toString(i), "9")));
        }
        return max - min;
    }

    public int[] leftRigthDifference(int[] nums) {
        int l = nums.length;
        int[] lsum = new int[l];
        int[] rsum = new int[l];
        int[] res = new int[l];
        for (int i = 1; i < l; i++)
            lsum[i] += (nums[i - 1] + lsum[i - 1]);
        for (int i = l - 2; i >= 0; i--)
            rsum[i] += (nums[i + 1] + rsum[i + 1]);
        for (int i = 0; i < l; i++)
            res[i] = Math.abs(lsum[i] - rsum[i]);
        return res;
    }

    public static String isValid(int x, int y, int z) {

        if (x == y && x == z) return "YES";
        else if (x != y && y != z && x != z) return "NO";
        else {
            if (x == y && x != z && z > x) return "YES";
            else if (x == z && x != y && y > x) return "YES";
            else if (y == z && y != x && x > y) return "YES";
        }
        return "NO";
    }

    public long findTheArrayConcVal(int[] nums) {
        long res = 0;
        int l = nums.length;
        for (int i = 0; i < l / 2; i++) {
            res += concat(nums[i], nums[l - i - 1]);
        }
        if (l % 2 == 0)
            res += nums[l / 2];
        return res;
    }

    public int concat(int a, int b) {
        StringBuilder res = new StringBuilder();
        res.append(a).append(b);
        System.out.println(res.toString());
        return Integer.parseInt(res.toString());
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        int l = words.length;
        int ql = queries.length;
        int[] rll = new int[l + 1];
        int[] res = new int[ql];
        for (int i = 1; i <= l; i++) {
            if (isVowel(words[i - 1].charAt(0)) && isVowel(words[i - 1].charAt(words[i - 1].length() - 1)))
                rll[i]++;
            rll[i] += rll[i - 1];
        }
        for (int i = 0; i < ql; i++) {
            res[i] = (rll[queries[i][1] + 1] - rll[queries[i][0]]);
        }
        return res;
    }

    public int getCommon(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        int s1 = 0, s2 = 0;
        while (s1 < l1 && s2 < l2) {
            System.out.println(nums1[s1] + " " + nums2[s2]);
            if (nums1[s1] == nums2[s2])
                return nums1[s1];
            else if (nums1[s1] < nums2[s2])
                return s1++;
            else
                s2++;
        }
        return -1;
    }

    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int dis1 = 0, dis2 = 0;
        boolean ispresent = false;
        for (String s : words) {
            if (s.equals(target)) {
                ispresent = true;
                break;
            }
        }
        if (!ispresent) return -1;
        for (int i = startIndex; i < words.length; i++) {
            if (words[i].equals(target))
                break;
            dis1++;
        }
        dis1 = Math.min(dis1, n + 1 - dis1);
        for (int i = startIndex; i >= 0; i--) {
            if (words[i].equals(target))
                break;
            dis2++;
        }
        dis2 = Math.min(dis2, n + 1 - dis2);
        return Math.min(dis1, dis2);
    }

    public int[] plusOne(int[] a) {
        int[] arr = new int[a.length + 1];
        int carry = 1, l = a.length;
        for (int i = l - 1; i >= 0; i--) {
            arr[i + 1] = a[i] + carry;
            if (arr[i + 1] == 10) {
                arr[i + 1] = 0;
                carry = 1;
            } else
                carry = 0;
        }
        System.out.println(carry);
        arr[0] = carry;
        if (arr[0] == 0) {
            int[] res = new int[l];
            for (int i = 1; i < l; i++)
                res[i - 1] = arr[i];
            return res;
        }
        return arr;
    }

    public int captureForts(int[] forts) {
        int sp = 0, ep = forts.length - 1;
        while (forts[sp] == 0)
            sp++;
        while (forts[ep] == 0)
            ep--;
        if (sp + 1 <= ep) return 0;
        int max = 0, t = 0;
        System.out.println(sp + " " + ep);
        for (int i = sp + 1; i < ep; i++) {
            while (forts[i] == 0) {
                i++;
                t++;
            }
            max = Math.max(max, t);
            t = 0;
        }
        return max;
    }

    public boolean check(int[] arr) {
        int l = arr.length;
        int si = l / 2;
        int sum = 0;
        for (int i : arr)
            sum += i;
        if (sum % 2 == 1 && si != 1)
            return false;
        if (sum % si != 0)
            return false;
        int val = sum / si;
        Arrays.sort(arr);
        for (int i = 0; i < l / 2; i++) {
            if (arr[i] + arr[l - 1 - i] != val)
                return false;
        }
        return true;
    }

    public int similarPairs(String[] words) {
        int res = 0, l = words.length;
        for (int i = 0; i < l - 1; i++) {
            for (int j = i + 1; j < l; j++) {
                if (similar(words[i], words[j])) {
                    res++;
                    System.out.println(words[i] + " " + words[j]);
                }

            }
        }
        return res;
    }

    public static boolean similar(String a, String j) {
        int[] arr = new int[26];
        for (char c : a.toCharArray())
            arr[c - 'a']++;
        for (char c : j.toCharArray()) {
            if (arr[c - 'a'] == 0)
                return false;
        }
        for (char c : j.toCharArray())
            arr[c - 'a'] = 0;
        for (int i : arr) {
            if (i == 0)
                return false;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        if (isPalindrome(s))
            return true;
        String result;
        for (int i = 0; i < s.length(); i++) {
            result = s.substring(0, i) + s.substring(i + 1);
            if (isPalindrome(result))
                return true;
        }
        return false;
    }

    public int distinctAverages(int[] nums) {
        HashSet<Double> set = new HashSet<>();
        Arrays.sort(nums);
        int l = nums.length;
        double t;
        for (int i = 0; i < l / 2; i++) {
            t = nums[i] + nums[l - i - 1];
            t /= 2;
            set.add(t);
        }
        return set.size();
    }

    public double[] convertTemperature(double celsius) {
        double[] res = {celsius + 273.15, (celsius * 1.8) + 32};
        return res;
    }

    public int unequalTriplets(int[] nums) {
        int res = 0, l = nums.length;
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                if (nums[i] != nums[j]) {
                    for (int k = j + 1; k < l; k++) {
                        if (nums[i] != nums[k] && nums[k] != nums[j])
                            res++;
                    }
                }
            }
        }
        return res;
    }

    public int maximumValue(String[] strs) {
        int res = 0;
        for (String s : strs) {
            if (isNumber(s))
                res = Math.max(res, Integer.parseInt(s));
            else
                res = Math.max(res, s.length());
        }
        return res;
    }

    public boolean isNumber(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public static boolean isPerfectSquare(int n) {
        int nn = (int) Math.sqrt(n);
        if (nn * nn == n) return true;
        return false;
    }

    public int garbageCollection(String[] garbage, int[] travel) {
        int res = 0;
        int gi = -1, mi = -1, pi = -1;
        int gl = garbage.length;
        for (int i = gl - 1; i >= 0; i--) {
            if (garbage[i].contains("G")) {
                gi = i;
                break;
            }
        }
        for (int i = gl - 1; i >= 0; i--) {
            if (garbage[i].contains("P")) {
                pi = i;
                break;
            }
        }
        for (int i = gl - 1; i >= 0; i--) {
            if (garbage[i].contains("M")) {
                mi = i;
                break;
            }
        }
        System.out.println(gi + " " + pi + " " + mi);
        for (int i = 1; i <= gi; i++) {
            res += travel[i - 1];
            if (garbage[i].contains("G"))
                res += count(garbage[i], 'G');
        }
        for (int i = 1; i <= mi; i++) {
            res += travel[i - 1];
            if (garbage[i].contains("M"))
                res += count(garbage[i], 'M');
        }
        for (int i = 1; i <= pi; i++) {
            res += travel[i - 1];
            if (garbage[i].contains("P"))
                res += count(garbage[i], 'P');
        }
        if (garbage[0].contains("P"))
            res += count(garbage[0], 'P');
        if (garbage[0].contains("M"))
            res += count(garbage[0], 'M');
        if (garbage[0].contains("G"))
            res += count(garbage[0], 'G');
        return res;
    }

    public int count(String s, char p) {
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c == (p))
                res++;
        }
        return res;
    }

    public int reverseInt(int n) {
        StringBuffer sbr = new StringBuffer(String.valueOf(n));
        return Integer.parseInt(sbr.reverse().toString());
    }

    public boolean equalFrequency(String word) {
        int[] arr = new int[26];
        for (char c : word.toCharArray())
            arr[c - 'a']++;
        HashSet<Integer> set = new HashSet<>();
        int max = -1;
        for (int i : arr) {
            if (i > 0)
                set.add(i);
            max = Math.max(max, i);
        }
        Integer[] inset = set.toArray(new Integer[set.size()]);
        if (set.size() != 2) return false;
        if (Math.abs(inset[0] - inset[1]) != 1) return false;
        int maxcnt = 0;
        for (int i : arr) {
            if (i == max)
                maxcnt++;
        }
        return maxcnt == 1 ? true : false;

    }

    public List<Boolean> prefixesDivBy5(int[] nums) {
        int l = nums.length;
        List<Boolean> ar = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        for (int i : nums) {
            res.append(i);
            if (Integer.parseInt(res.toString(), 2) % 5 == 0)
                ar.add(true);
            else
                ar.add(false);
        }
        return ar;
    }

    static long factorial(int i) {
        long result = 1;

        for (int factor = 2; factor <= i; factor++) {
            result *= factor;
        }

        return result;
    }

    static void reverseRange(int[] arr, int start, int end) {
        int[] res = new int[end - start + 1];
        for (int i = start; i < end; i++)
            res[i - start] = arr[i];
        reverse(res);
        for (int i : res)
            System.out.print(i + "--");
        System.out.println("");
        for (int i = start; i < end; i++)
            arr[i] = res[i - start];
    }

    class LRU {
/*      Pseudo code
        int size; // size of cache
        double linked list  // maintains elements;
        head
        tail
        // constructor {
        size = n
        dll = new dll
        head = tail
        }

        boolean checkforhit(int k){
            for(tail -> head)
                if(hit){
                    updatedll()
                    return true;
                }
            return false
        }

        void put(int k){
            if(checkforhit(k))
                get(k)
            else
                head = k
                tail --;
        }

        int get(dll)
            search and return k

         void updatedll(int k){
               //find node where val == k;
               from val to head
                    shift values back
               head = k
         }


*/
    }

    class Bitset {

        int[] arr;
        boolean allv;
        boolean onev;
        int cnt;
        int l;

        public Bitset(int size) {
            arr = new int[size];
            l = size;
            Arrays.fill(arr, 0);
            allv = false;
            onev = false;
            cnt = 0;
        }

        public void fix(int idx) {
            if (idx <= this.arr.length && arr[idx] == 0) {
                arr[idx] = 1;
                onev = true;
                cnt++;
            }
            if (cnt == l)
                allv = true;

        }

        public void unfix(int idx) {
            if (idx <= this.arr.length && arr[idx] == 1) {
                arr[idx] = 0;
                allv = false;
                cnt--;
            }
            if (cnt == 0)
                onev = false;

        }

        public void flip() {

            for (int i = 0; i < l; i++) {
                if (arr[i] == 0)
                    arr[i] = 1;
                else
                    arr[i] = 0;
            }
            cnt = l - cnt;
            if (cnt == 0)
                onev = false;
            else
                onev = true;
            if (cnt == l)
                allv = true;
            else
                allv = false;
        }

        public boolean all() {

            return this.allv;
        }

        public boolean one() {

            return this.onev;
        }

        public int count() {

            return cnt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i : arr)
                sb.append(i);
            return sb.toString();
        }
    }

    public long smallestNumber(long num) {
        long res = 0;
        //Arrays.sort(pl,Comparator.comparingInt(ar -> ar.b).thenComparingInt());
        ArrayList<Long> arr = new ArrayList<>();
        long n = Math.abs(num);
        int zc = 0;
        if (num == 0)
            return 0;
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            arr.add(n % 10);
            if (n % 10 == 0)
                zc++;
            n /= 10;
        }
        if (num > 0) {
            Collections.sort(arr);
            sb.append(arr.get(zc));
            for (int i = 0; i < zc; i++)
                sb.append(0);
            for (int i = zc + 1; i < arr.size(); i++)
                sb.append(arr.get(i));

        } else {
            Collections.sort(arr, Collections.reverseOrder());
            for (int i = 0; i < arr.size(); i++)
                sb.append(arr.get(i));
        }
        res = Long.parseLong(sb.toString());
        if (num < 0)
            res *= (-1);
        return res;
    }

    static int getDigitSum(long n) {
        int t = 0;
        while (n > 0) {
            t += (n % 10);
            n /= 10;
        }
        return t;
    }

    static class Node {
        int n;
        int a;
        int s;

        public int getA() {
            return a;
        }

        public int getAvg() {
            return s;
        }

        Node(int a, int s, int n) {
            this.a = a;
            this.s = s;
            this.n = n;
        }
    }
/*

sorting based on number then age

Comparator<Triplet> employeeAgeComparator
                = Comparator.comparingInt(Triplet::getA).reversed();
        final Function<Triplet, String> byTheirName = person -> person.getName();
        Arrays.sort(tarr,employeeAgeComparator.thenComparing(byTheirName));
 */
//Updation Required
    //Fenwick Tree (customisable)
    //Segment Tree (customisable)

    //-----CURRENTLY PRESENT-------//
    //Graph
    //DSU
    //powerMODe
    //power
    //Segment Tree (work on this one)
    //Prime Sieve
    //Count Divisors
    //Next Permutation
    //Get NCR
    //isVowel
    //Sort (int)
    //Sort (long)
    //Binomial Coefficient
    //Pair
    //Triplet
    //lcm (int & long)
    //gcd (int & long)
    //gcd (for binomial coefficient)
    //swap (int & char)
    //reverse

    //Fast input and output

    //-------------------------------------------------------------------
    //-------------------------------------------------------------------
    //-------------------------------------------------------------------
    //-------------------------------------------------------------------
    //-------------------------------------------------------------------


    static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) return false;
        }
        return true;
    }

    static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    //GRAPH (basic structure)
    public static class Graph {
        public int V;
        public ArrayList<ArrayList<Integer>> edges;

        //2 -> [0,1,2] (current)
        Graph(int V) {
            this.V = V;
            edges = new ArrayList<>(V + 1);
            for (int i = 0; i <= V; i++) {
                edges.add(new ArrayList<>());
            }
        }

        public void addEdge(int from, int to) {
            edges.get(from).add(to);
        }
    }

    //DSU (path and rank optimised)
    public static class DisjointUnionSets {
        int[] rank, parent;
        int n;

        public DisjointUnionSets(int n) {
            rank = new int[n];
            parent = new int[n];
            Arrays.fill(rank, 1);
            Arrays.fill(parent, -1);
            this.n = n;
        }

        public int find(int curr) {
            if (parent[curr] == -1)
                return curr;

            //path compression optimisation
            return parent[curr] = find(parent[curr]);
        }

        public void union(int a, int b) {
            int s1 = find(a);
            int s2 = find(b);

            if (s1 != s2) {
                if (rank[s1] < rank[s2]) {
                    parent[s1] = s2;
                    rank[s2] += rank[s1];
                } else {
                    parent[s2] = s1;
                    rank[s1] += rank[s2];
                }
            }
        }
    }

    //with mod
    public static long powerMOD(long x, long y) {
        long res = 1L;
        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) != 0) {
                x %= mod;
                res %= mod;
                res = (res * x) % mod;
            }
            // y must be even now
            y = y >> 1; // y = y/2
            x %= mod;
            x = (x * x) % mod;  // Change x to x^2
        }
        return res % mod;
    }

    //without mod
    public static long power(long x, long y) {
        long res = 1L;
        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) != 0) {
                res = (res * x);
            }
            // y must be even now
            y = y >> 1; // y = y/
            x = (x * x);
        }
        return res;
    }

    public static class segmentTree {

        public long[] arr;
        public long[] tree;
        public long[] lazy;

        segmentTree(long[] array) {
            int n = array.length;
            arr = new long[n];
            for (int i = 0; i < n; i++) arr[i] = array[i];
            tree = new long[4 * n + 1];
            lazy = new long[4 * n + 1];
        }

        public void build(int[] arr, int s, int e, int[] tree, int index) {

            if (s == e) {
                tree[index] = arr[s];
                return;
            }

            //otherwise divide in two parts and fill both sides simply
            int mid = (s + e) / 2;
            build(arr, s, mid, tree, 2 * index);
            build(arr, mid + 1, e, tree, 2 * index + 1);

            //who will build the current position dude
            tree[index] = Math.min(tree[2 * index], tree[2 * index + 1]);
        }

        public int query(int sr, int er, int sc, int ec, int index, int[] tree) {

            if (lazy[index] != 0) {
                tree[index] += lazy[index];

                if (sc != ec) {
                    lazy[2 * index + 1] += lazy[index];
                    lazy[2 * index] += lazy[index];
                }

                lazy[index] = 0;
            }

            //no overlap
            if (sr > ec || sc > er) return Integer.MAX_VALUE;

            //found the index baby
            if (sr <= sc && ec <= er) return tree[index];

            //finding the index on both sides hehehehhe
            int mid = (sc + ec) / 2;
            int left = query(sr, er, sc, mid, 2 * index, tree);
            int right = query(sr, er, mid + 1, ec, 2 * index + 1, tree);

            return Integer.min(left, right);
        }

        //now we will do point update implementation
        //it should be simple then we expected for sure
        public void update(int index, int indexr, int increment, int[] tree, int s, int e) {

            if (lazy[index] != 0) {
                tree[index] += lazy[index];

                if (s != e) {
                    lazy[2 * index + 1] = lazy[index];
                    lazy[2 * index] = lazy[index];
                }

                lazy[index] = 0;
            }

            //no overlap
            if (indexr < s || indexr > e) return;

            //found the required index
            if (s == e) {
                tree[index] += increment;
                return;
            }

            //search for the index on both sides
            int mid = (s + e) / 2;
            update(2 * index, indexr, increment, tree, s, mid);
            update(2 * index + 1, indexr, increment, tree, mid + 1, e);

            //now update the current range simply
            tree[index] = Math.min(tree[2 * index + 1], tree[2 * index]);
        }

        public void rangeUpdate(int[] tree, int index, int s, int e, int sr, int er, int increment) {

            //if not at all in the same range
            if (e < sr || er < s) return;

            //complete then also move forward
            if (s == e) {
                tree[index] += increment;
                return;
            }

            //otherwise move in both subparts
            int mid = (s + e) / 2;
            rangeUpdate(tree, 2 * index, s, mid, sr, er, increment);
            rangeUpdate(tree, 2 * index + 1, mid + 1, e, sr, er, increment);

            //update current range too na
            //i always forget this step for some reasons hehehe, idiot
            tree[index] = Math.min(tree[2 * index], tree[2 * index + 1]);
        }

        public void rangeUpdateLazy(int[] tree, int index, int s, int e, int sr, int er, int increment) {

            //update lazy values
            //resolve lazy value before going down
            if (lazy[index] != 0) {
                tree[index] += lazy[index];

                if (s != e) {
                    lazy[2 * index + 1] += lazy[index];
                    lazy[2 * index] += lazy[index];
                }

                lazy[index] = 0;
            }

            //no overlap case
            if (sr > e || s > er) return;

            //complete overlap
            if (sr <= s && er >= e) {
                tree[index] += increment;

                if (s != e) {
                    lazy[2 * index + 1] += increment;
                    lazy[2 * index] += increment;
                }
                return;
            }

            //otherwise go on both left and right side and do your shit
            int mid = (s + e) / 2;
            rangeUpdateLazy(tree, 2 * index, s, mid, sr, er, increment);
            rangeUpdateLazy(tree, 2 * index + 1, mid + 1, e, sr, er, increment);

            tree[index] = Math.min(tree[2 * index + 1], tree[2 * index]);
            return;

        }

    }

    //ceil int
    static int ceil(int x, int y) {
        return (x % y == 0 ? x / y : (x / y + 1));
    }

    //ceil long
    static long ceil(long x, long y) {
        return (x % y == 0 ? x / y : (x / y + 1));
    }

    //sqrt
    static long sqrt(long z) {
        long sqz = (long) Math.sqrt(z);
        while (sqz * 1L * sqz < z) {
            sqz++;
        }
        while (sqz * 1L * sqz > z) {
            sqz--;
        }
        return sqz;
    }

    //log base 2
    static int log2(int N) {
        int result = (int) (Math.log(N) / Math.log(2));
        return result;
    }

    //power of two
    static boolean isPowerOfTwo(int n) {
        if (n == 0)
            return false;

        while (n != 1) {
            if (n % 2 != 0)
                return false;
            n = n / 2;
        }
        return true;
    }

    public static int lower_bound(int[] arr, int x) {
        int low = 0, high = arr.length - 1, mid = -1;
        int ans = -1;
        while (low <= high) {
            mid = (low + high) / 2;

            if (arr[mid] > x) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }

        return ans;
    }

    public static int upper_bound(int[] arr, int x) {
        int low = 0, high = arr.length - 1, mid = -1;
        int ans = arr.length;
        while (low < high) {
            mid = (low + high) / 2;

            if (arr[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    //prime sieve
    public static void primeSieve(int n) {
        BitSet bitset = new BitSet(n + 1);
        for (long i = 0; i < n; i++) {
            if (i == 0 || i == 1) {
                bitset.set((int) i);
                continue;
            }
            if (bitset.get((int) i)) continue;
            primeNumbers.add((int) i);
            for (long j = i; j <= n; j += i)
                bitset.set((int) j);
        }
    }

    //number of divisors
    public static int countDivisors(long number) {
        if (number == 1) return 1;
        List<Integer> primeFactors = new ArrayList<>();
        int index = 0;
        long curr = primeNumbers.get(index);
        while (curr * curr <= number) {
            while (number % curr == 0) {
                number = number / curr;
                primeFactors.add((int) curr);
            }
            index++;
            curr = primeNumbers.get(index);
        }

        if (number != 1) primeFactors.add((int) number);
        int current = primeFactors.get(0);
        int totalDivisors = 1;
        int currentCount = 2;
        for (int i = 1; i < primeFactors.size(); i++) {
            if (primeFactors.get(i) == current) {
                currentCount++;
            } else {
                totalDivisors *= currentCount;
                currentCount = 2;
                current = primeFactors.get(i);
            }
        }
        totalDivisors *= currentCount;
        return totalDivisors;
    }

    //now adding next permutation function to java hehe
    public static boolean next_permutation(int[] p) {
        for (int a = p.length - 2; a >= 0; --a)
            if (p[a] < p[a + 1])
                for (int b = p.length - 1; ; --b)
                    if (p[b] > p[a]) {
                        int t = p[a];
                        p[a] = p[b];
                        p[b] = t;
                        for (++a, b = p.length - 1; a < b; ++a, --b) {
                            t = p[a];
                            p[a] = p[b];
                            p[b] = t;
                        }
                        return true;
                    }
        return false;
    }

    //finding the value of NCR in O(RlogN) time and O(1) space
    public static long getNcR(int n, int r) {
        long p = 1, k = 1;
        if (n - r < r) r = n - r;

        if (r != 0) {
            while (r > 0) {
                p *= n;
                k *= r;
                long m = __gcd(p, k);
                p /= m;
                k /= m;
                n--;
                r--;
            }
        } else {
            p = 1;
        }
        return p;
    }

    //is vowel function
    public static boolean isVowel(char c) {
        return (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U');
    }

    public static boolean isSingleHole(char c) {
        return (c == 'A' || c == 'D' || c == 'O' || c == 'R' || c == 'P' || c == 'Q');
    }

    //to sort the array with better method
    public static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a) l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++) a[i] = l.get(i);
    }

    //sort long
    public static void sort(long[] a) {
        ArrayList<Long> l = new ArrayList<>();
        for (long i : a) l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++) a[i] = l.get(i);
    }

    //for calculating binomialCoeff
    public static int binomialCoeff(int n, int k) {
        int C[] = new int[k + 1];
        // nC0 is 1
        C[0] = 1;
        for (int i = 1; i <= n; i++) {
            // Compute next row of pascal
            // triangle using the previous row
            for (int j = Math.min(i, k); j > 0; j--)
                C[j] = C[j] + C[j - 1];
        }
        return C[k];
    }

    //Pair with int int
    public static class Pair {
        public int a;
        public int b;
        public String s;
        public long l;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        Pair(long a, int b) {
            this.l = a;
            this.b = b;
        }

        Pair(int a, String b) {
            this.a = a;
            this.s = b;
        }

        public int getA() {
            return this.a;
        }

        @Override
        public String toString() {
            return a + " -> " + b;
        }
    }


    //Triplet with int int int
    public static class Triplet {

        public int a;
        public int b;
        public int c;
        public String name;

        Triplet(String name, int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.name = name;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Triplet{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    //Shortcut function
    public static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    //let's make one for calculating lcm basically
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    //int version for gcd
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;

        return gcd(b, a % b);
    }

    //long version for gcd
    public static long gcd(long a, long b) {
        if (b == 0)
            return a;

        return gcd(b, a % b);
    }

    //for ncr calculator(ignore this code)
    public static long __gcd(long n1, long n2) {
        long gcd = 1;
        for (int i = 1; i <= n1 && i <= n2; ++i) {
            // Checks if i is factor of both integers
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    static boolean isOddBitsSet(int n) {
        if (Integer.bitCount(n) % 2 == 0)
            return true;
        return false;
    }

    //swapping two elements in an array
    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void swap(long[] arr, int left, int right) {
        long temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    //for char array
    public static void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    //reversing an array
    public static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    public static void reverse(long[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    public static long expo(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1L) res = (res * a) % mod;  //think about this one for a second
            a = (a * a) % mod;
            b = b >> 1;
        }
        return res;
    }

    //SOME EXTRA DOPE FUNCTIONS
    public static long mminvprime(long a, long b) {
        return expo(a, b - 2, b);
    }

    public static long mod_add(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (((a + b) % m) + m) % m;
    }

    public static long mod_sub(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (((a - b) % m) + m) % m;
    }

    public static long mod_mul(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (((a * b) % m) + m) % m;
    }

    public static long mod_div(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (mod_mul(a, mminvprime(b, m), m) + m) % m;
    }

    //O(n) every single time remember that
    public static long nCr(long N, long K, long mod) {
        long upper = 1L;
        long lower = 1L;
        long lowerr = 1L;

        for (long i = 1; i <= N; i++) {
            upper = mod_mul(upper, i, mod);
        }

        for (long i = 1; i <= K; i++) {
            lower = mod_mul(lower, i, mod);
        }

        for (long i = 1; i <= (N - K); i++) {
            lowerr = mod_mul(lowerr, i, mod);
        }

        // out.println(upper + " " + lower + " " + lowerr);
        long answer = mod_mul(lower, lowerr, mod);
        answer = mod_div(upper, answer, mod);

        return answer;
    }

    // ll *fact = new ll[2 * n + 1];
    // ll *ifact = new ll[2 * n + 1];
    // fact[0] = 1;
    // ifact[0] = 1;
    // for (ll i = 1; i <= 2 * n; i++)
    // {
    // 	fact[i] = mod_mul(fact[i - 1], i, MOD1);
    // 	ifact[i] = mminvprime(fact[i], MOD1);
    // }
    //ifact is basically inverse factorial in here!!!!!(imp)
    public static long combination(long n, long r, long m, long[] fact, long[] ifact) {
        long val1 = fact[(int) n];
        long val2 = ifact[(int) (n - r)];
        long val3 = ifact[(int) r];
        return (((val1 * val2) % m) * val3) % m;
    }

    static int[] readArray(int N) {
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            res[i] = (int) sc.nextInt();
        }
        return res;
    }

    static double[] readArrayDouble(int N) {
        double[] res = new double[N];
        for (int i = 0; i < N; i++) {
            res[i] = sc.nextDouble();
        }
        return res;
    }

    static long[] readArrayLong(int N) {
        long[] res = new long[N];
        for (int i = 0; i < N; i++) {
            res[i] = sc.nextLong();
        }
        return res;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}