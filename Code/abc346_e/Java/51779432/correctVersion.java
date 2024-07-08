public class Main{
  public static void main(String[] args){
    var sc=new java.util.Scanner(System.in);
    int H=sc.nextInt();
    int W=sc.nextInt();
    int M=sc.nextInt();
    int[] T=new int[M],A=new int[M],X=new int[M];
    
    for(int i=0;i<M;i++){
      T[i]=sc.nextInt();
      A[i]=sc.nextInt()-1;
      X[i]=sc.nextInt();
    }
    
    var map=new java.util.HashMap<Integer,Long>();
    boolean[] hused=new boolean[H],vused=new boolean[W];
    
    for(int i=M-1;i>=0;i--){
      int t=T[i];
      int a=A[i];
      int x=X[i];
      
      if(t==1){
        if(!hused[a]){
          hused[a]=true;
          map.put(x,map.getOrDefault(x,0l)+W);
          H--;
        }
      }else{
        if(!vused[a]){
          vused[a]=true;
          map.put(x,map.getOrDefault(x,0l)+H);
          W--;
        }
      }
    }
    map.put(0,map.getOrDefault(0,0l)+(long)W*H);
    
    var colors=new java.util.ArrayList<Integer>();
    for(var set:map.entrySet()){
      if(set.getValue()>0)colors.add(set.getKey());
    }
    
    java.util.Collections.sort(colors);
    
    System.out.println(colors.size());
    for(int color:colors)System.out.println(color+" "+map.get(color));
  }
}