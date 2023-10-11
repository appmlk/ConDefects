import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int h=sc.nextInt();
    int w=sc.nextInt();
    String[][] Cookie=new String[h][w];
    String[] Cookie0=new String[h];
    int [] sunukex=new int[h];
    int [] sunukey=new int[w];
    int count=0;

    for(int i=0;i<h;i++){
      Cookie0[i]=sc.next();
      String split[]=Cookie0[i].split("");
      for(int j=0;j<w;j++){
        Cookie[i][j]=split[j];
        if(Cookie[i][j].equals("#")){
          count++;
        }
      }
      sunukex[i]=count;
      //System.out.println("# x："+sunukex[i]);
      count=0;
    }

    int many=sunukex[0];
    for(int i=1;i<h;i++){
      if(many<sunukex[i]){
        many=sunukex[i];
      }
    }

    //System.out.println("many x:"+many);

    int x=0;
    for(int i=0;i<h;i++){
      if(sunukex[i]==many-1){
        x=i+1;
      }
    }
    //System.out.println("x:"+x);

    for(int i=0;i<w;i++){
      for(int j=0;j<h;j++){
        if(Cookie[j][i].equals("#")){
          count++;
        }
      }
      sunukey[i]=count;
      //System.out.println("# y："+sunukey[i]);
      count=0;
    }
    many=sunukey[0];
    for(int i=1;i<w;i++){
      if(many<sunukey[i]){
        many=sunukey[i];
      }
    }

    //System.out.println("many y:"+many);

    int y=0;
    for(int i=0;i<w;i++){
      if(sunukey[i]==many-1){
        y=i+1;
      }
    }
    //System.out.println("y:"+y);
    System.out.println(x+" "+y);
    sc.close();
  }
}