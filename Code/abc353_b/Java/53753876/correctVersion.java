import java.util.*;
public class Main{
  public static void main(String[]args){
    Scanner scan = new Scanner(System.in);
    Queue<Integer> queue = new PriorityQueue<Integer> (); 
    int [] line=new int[scan.nextInt()];
    int cap = scan.nextInt();
    int on = 0;
    int count =1;
    for(int j =0;j<line.length;j++)
        line[j]=scan.nextInt();
    for(int i =0;i<line.length;i++){
      if(line[i]<=cap-on)
        on = on + line[i];
      else{
        count++;
        on = line[i];
      }
    }
    System.out.println(count);}}