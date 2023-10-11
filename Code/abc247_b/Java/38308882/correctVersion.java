import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String[] last = new String[n];
        String[] first = new String[n];
        Set<String> set = new HashSet<String>();
        boolean judge =false,judge2=false;
        for(int i=0;i<n;i++){
            last[i] = scan.next();
            first[i] = scan.next();
            set.add((last[i]+" "+first[i]));
        }
        if(set.size()!=n){
            System.out.println("No");
            System.exit(0);
        }
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(last[i].equals(last[j])||last[i].equals(first[j])) judge = true;
                if(first[i].equals(first[j]) || first[i].equals(last[j])) judge2 =true;
            }
        }

        if(!judge||!judge2) System.out.println("Yes");
        else  System.out.println("No");

    }
}

