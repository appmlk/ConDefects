import java.util.Scanner;

class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String[] array = new String[n];
        for(int i = 0; i < n; i++){
            array[i] = sc.next();
        }

        String result = "No";
        for(int i = 0; i < n-1; i++){
            if(result.equals("Yes")){
                break;
            }
            String a = array[i];
            for(int j = i+1; j < n; j++){
                String b = array[j];
                String c = a+b;
                String d = b+a;
                System.out.println(c);
                System.out.println(d);
                int cCnt = 0;
                int dCnt = 0;
                for(int k = 0; k < c.length(); k++){
                    if(c.charAt(k) == c.charAt(c.length()-1-k)){
                        cCnt+=1;
                    }
                }
                for(int l = 0; l < d.length(); l++){
                    if(d.charAt(l) == d.charAt(d.length()-1-l)){
                        dCnt+=1;
                    }
                }
                if(c.length() == cCnt || d.length() == dCnt){
                    result = "Yes";
                    break;
                }
            }
        }
        System.out.println(result);        
    }
}