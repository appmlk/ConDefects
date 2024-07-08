import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main
{
  public static void main(String[] args){
    ArrayList<Integer> ball = new ArrayList<Integer>();
    Scanner scan = new Scanner(System.in);
  
    int number = scan.nextInt();
    scan.nextLine();  
    String inputs = scan.nextLine();
    String[] n = inputs.split(" ");
    ArrayList<Integer> inp = new ArrayList<Integer>();
    for(int i= 0;i<n.length;i++){
        inp.add(Integer.parseInt(n[i]));
    }
    ball.add(inp.get(0));
    for(int k = 1; k<inp.size();k++){
        int addnumber = inp.get(k);
        if(ball.get(ball.size()-1) != addnumber){
            ball.add(addnumber);
            System.out.println(addnumber+"add");
        }else{

        while(ball.get(ball.size()-1) == addnumber){
            System.out.println(addnumber+"remove");
            ball.remove(ball.size()-1);
            addnumber= addnumber+1;
            if(ball.size()==0){
                break;
            }
            
        }
        ball.add(addnumber);
    }
    }

    System.out.println(ball.size());
  }

  
}