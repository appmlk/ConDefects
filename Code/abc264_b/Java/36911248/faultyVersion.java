import java.util.*;

public class Main {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        int r = Integer.parseInt(sc.next());
        int c = Integer.parseInt(sc.next());
        
        
        // rが奇数なら黒
        // c が r ~ 15-(r-1) 黒
        // c が 0<r が奇数なら黒
        if(r==1 || r==15 || c==1 || r==15){
            System.out.println("black");
        }
        else if(r==2 || r==14 || c==2 || c==14){
            System.out.println("white");
        }
        else if(r==3 || r==13 || c==3 || c==13){
            System.out.println("black");
        }
        else if(r==4 || r==12 || c==4 || c==12){
            System.out.println("white");
        }
        else if(r==5 || r==11 || c==5 || c==11){
            System.out.println("black");
        }
        else if(r==6 || r==10 || c==6 || c==10){
            System.out.println("white");
        }
        else if(r==7 || r==9 || c==7 || c==9){
            System.out.println("black");
        }
        else if(r==8){
            System.out.println("white");
        }
    }
}

// oj t -c "java Main.java" -d ./test/
// acc submit Main.java -- -l 4005