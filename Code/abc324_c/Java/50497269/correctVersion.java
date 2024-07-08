import java.util.ArrayList;
import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=Integer.parseInt(sc.next());
        String t=sc.next();

        ArrayList<Integer>ans=new ArrayList<>();

        for(int i=1;i<=n;i++){
            String s=sc.next();
            if(Math.abs(s.length()-t.length())>1){
                continue;
            }
            if(s.length()==t.length()){
                if(isMatch(s,t)){
                    ans.add(i);
                }
                continue;
            }
            String longerStr;
            String shorterStr;
            if(s.length()>t.length()){
                longerStr=s;
                shorterStr=t;
            }else{
                longerStr=t;
                shorterStr=s;
            }
            if(isMatchLongShort(longerStr,shorterStr)){
                ans.add(i);
            }
        }
        System.out.println(ans.size());
        for(int i=0;i<ans.size();i++){
            if(i>0){
                System.out.print(" ");
            }
            System.out.print(ans.get(i));
        }
        System.out.println();
    }

    public static boolean isMatch(String s,String t){
        int diffCount=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=t.charAt(i)){
                diffCount++;
            }
        }
        if(diffCount<=1){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isMatchLongShort(String longerStr,String shorterStr){
        int diffCount=0;
        int longerStrOffset=0;
        for(int i=0;i<shorterStr.length();i++){
            if(longerStr.charAt(i+longerStrOffset)!=shorterStr.charAt(i)){
                diffCount++;
                if(longerStrOffset<1){
                    longerStrOffset++;
                    i--;
                }
            }
        }
        if(diffCount<=1){
            return true;
        }else{
            return false;
        }
    }
}