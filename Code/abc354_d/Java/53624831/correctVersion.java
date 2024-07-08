import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    
        var sc = new Scanner(System.in);
        
        long left = Long.parseLong(sc.next());
        long bottom = Long.parseLong(sc.next());
        long right = Long.parseLong(sc.next());
        long top = Long.parseLong(sc.next());
        
        long ans = 0;
        int l = (int) (left % 4 + 4) % 4;
        int b = (int) (bottom % 2 + 2) % 2;
        
        right -= left;
        left = 0;
        top -= bottom;
        bottom = 0;
        
        switch(l){
            case 0 -> {
                if(b == 0){
                    ans += (right / 2) * ((top + 1) / 2);
                    ans += ((right + 1) / 2) * (top / 2);
                    ans += ((right + 3) / 4) * ((top + 1) / 2) * 2;
                    ans += ((right + 2) / 4) * (top / 2) * 2;
                }else{
                    ans += ((right + 1) / 2) * ((top + 1) / 2);
                    ans += (right / 2) * (top / 2);
                    ans += ((right + 2) / 4) * ((top + 1) / 2) * 2;
                    ans += ((right + 3) / 4) * (top / 2) * 2;
                }
            }
            case 1 -> {
                if(b == 0){
                    ans += ((right + 1) / 2) * ((top + 1) / 2);
                    ans += (right / 2) * (top / 2);
                    ans += (right / 4) * ((top + 1) / 2) * 2;
                    ans += ((right + 3) / 4) * (top / 2) * 2;
                }else{
                    ans += (right / 2) * ((top + 1) / 2);
                    ans += ((right + 1) / 2) * (top / 2);
                    ans += ((right + 3) / 4) * ((top + 1) / 2) * 2;
                    ans += (right / 4) * (top / 2) * 2;
                }
            }
            case 2 -> {
                if(b == 0){
                    ans += (right / 2) * ((top + 1) / 2);
                    ans += ((right + 1) / 2) * (top / 2);
                    ans += ((right + 1) / 4) * ((top + 1) / 2) * 2;
                    ans += (right / 4) * (top / 2) * 2;
                }else{
                    ans += ((right + 1) / 2) * ((top + 1) / 2);
                    ans += (right / 2) * (top / 2);
                    ans += (right / 4) * ((top + 1) / 2) * 2;
                    ans += ((right + 1) / 4) * (top / 2) * 2;
                }
            }
            case 3 -> {
                if(b == 0){
                    ans += ((right + 1) / 2) * ((top + 1) / 2);
                    ans += (right / 2) * (top / 2);
                    ans += ((right + 2) / 4) * ((top + 1) / 2) * 2;
                    ans += ((right + 1) / 4) * (top / 2) * 2;
                }else{
                    ans += (right / 2) * ((top + 1) / 2);
                    ans += ((right + 1) / 2) * (top / 2);
                    ans += ((right + 1) / 4) * ((top + 1) / 2) * 2;
                    ans += ((right + 2) / 4) * (top / 2) * 2;
                }
            }
        }
        System.out.println(ans);
    }
}