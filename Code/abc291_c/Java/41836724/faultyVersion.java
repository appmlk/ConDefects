import java.util.*;
class Main{
    public static void main (String[]args){
        Scanner scanner =new Scanner(System.in);
        int n=scanner.nextInt();
        
        int x=0;
        int y=0;
        Set<String> ans=new HashSet<>();
        ans.add(0 + "," + 0);
        String s=scanner.next();
        
        for(int i=0;i<n;i++){
            
            if(s.charAt(i)=='R'){
                x++;
            } else if(s.charAt(i)=='L'){
                x--;
            } else if(s.charAt(i)=='U'){
                y++;
            } else{
                y--;
            }
            if(ans.contains(x + "," + y)){
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
        
    }
}