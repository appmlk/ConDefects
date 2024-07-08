import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        long cbig = scanner.nextLong();
        int ccnt = 0;
        String cstr = "";
        while(cbig > 0){
            cstr = cbig % 2 + cstr;
            if(cbig%2 == 1)ccnt++;
            cbig /= 2;
        }

        

        if(a + b >= ccnt && (a + b - ccnt) % 2 == 0 && Math.abs(a - b) <= ccnt){
            ccnt = (a + b) - ccnt;
            ccnt /= 2;
            String astr = "";
            String bstr = "";
            a -= ccnt;
            b -= ccnt;

            for(int i = cstr.length() - 1; i >= 0; i--){
                if(cstr.charAt(i) == '0'){
                    if(ccnt > 0){
                        ccnt--;
                        astr = "1" + astr;
                        bstr = "1" + bstr;
                    }
                    else{
                        astr = "0" + astr;
                        bstr = "0" + bstr; 
                    }
                }
                else{
                    if(a > 0){
                        a--;
                        astr = "1" + astr;
                        bstr = "0" + bstr;
                    }
                    else{
                        b--;
                        astr = "0" + astr;
                        bstr = "1" + bstr;
                    }
                }
            }

            for(int i = 0; i < ccnt; i++){
                astr = "1" + astr;
                bstr = "1" + bstr;
            }
            long aans = 0;

            for(int i = 0; i < astr.length(); i++){
                aans *= 2;
                aans += (astr.charAt(i) - '0');
            }

            long bans = 0;
            for(int i = 0; i < bstr.length(); i++){
                bans *= 2;
                bans += (bstr.charAt(i) - '0');
            }

            if(aans > Math.pow(2, 60) || bans > Math.pow(2, 60)){
                System.out.println(-1);
                return;
            }

            System.out.println(aans + " " + bans);
        } 
        else{
            System.out.println(-1);
        }
    }
}