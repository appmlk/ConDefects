import java.util.ArrayList;
public class Main{
  public static void main(String[] args){
    var sc=new java.util.Scanner(System.in);
    int[] ban=new int[9],possess=new int[9];
    for(int i=0;i<9;i++)ban[i]=sc.nextInt();
    
    System.out.println(solve(ban,possess,1)==1?"Takahashi":"Aoki");
  }
  
  private static int solve(int[] ban,int[] possess,int player){
    int[][] check=new int[][]{
      {0,1,2},
      {3,4,5},
      {6,7,8},
      {0,3,6},
      {1,4,7},
      {2,5,8},
      {0,4,8},
      {2,4,6}
    };
    
    for(int[] ch:check){
      if(Math.abs(possess[ch[0]]+possess[ch[1]]+possess[ch[2]])==3)return possess[ch[0]];
    }
    
    boolean noSpace=true;
    int t1=0,t2=0;
    for(int i=0;i<9;i++){
      switch(possess[i]){
        case 0:
          noSpace=false;
          possess[i]=player;
          int result=solve(ban,possess,-player);
          possess[i]=0;
          if(result==player)return player;
          break;
        case 1:
          t1+=ban[i];
          break;
        default:
          t2+=ban[i];
          break;
      }
    }
    if(noSpace)return t1>t2?1:-1;
    else return -player;
  }
}