import java.util.*;
 class Main {
  public static void main(String args[]) {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int m=sc.nextInt();
    sc.nextLine();
    char[][] ch=new char[n][m];
    for(int i=0;i<n;i++) {
      String s=sc.nextLine();
      for(int j=0;j<m;j++) ch[i][j]=s.charAt(j);
    }
    int row=0, size=-1;
    // TreeMap<Integer, Integer> tm=new TreeMap<>();
    // for(int i=0;i<n;i++) {
    //     int l=0;
    //     for(int j=0;j<m;j++) {
    //         if(ch[i][j]=='#') l++;
    //     }
    //     if(l!=0) tm.put(l, i);
    // }
    // for(Map.Entry<Integer, Integer> ele:tm.entrySet()) {
    //     row=ele.getValue();
    //     break;
    // }
    for(int i=0;i<n;i++) {//row traversal
      int l=0;//cookie row length of ith row
      for(int j=0;j<m;j++) {
        if(ch[i][j]=='#') l++;
      }
      if(size==-1 && l!=0) {size=l;row=i;}
      if(size>l && l!=0) {//if there exist a cookie and its strictly smaller than ongoing size
        row=i;
        size=l;
      }
    }
    //we have the row number where def of cookie occur
    int col=0;
    boolean check=false;
    for(int j=0;row-1>=0 && j<m;j++) {//checking if upper row has any cookies
      if(ch[row-1][j]=='#') {
        check=true;
      }
    }
    int c=0;
    if(check) c=row-1;
    else c=row+1;
    for(int j=0;j<m;j++) {
      if(ch[c][j]!=ch[row][j]) {
        col=j;
        break;
      }
    }
    System.out.println((row+1)+" "+(col+1));
  }
}