import java.util.Scanner;
class Main {
  static int max;
  public static void main(String args[]) {
    Scanner sc=new Scanner(System.in);
    char[] ch=sc.next().toCharArray();
    permute(ch, 0, ch.length-1);
    System.out.println(max);
  }
  static void permute(char[] ch, int l, int r) {
    if(l==r) {
      for(int i=1;i<ch.length-1;i++) {
        String s1=String.valueOf(ch).substring(0,i);
        String s2=String.valueOf(ch).substring(i);
        if(s1.charAt(0)!='0' && s2.charAt(0)!='0') {
          max=Math.max(max, Integer.parseInt(s1)*Integer.parseInt(s2));
        }
      }
    }
    else {
      for(int i=l;i<=r;i++) {
        swap(ch, i, l);
        permute(ch, l+1, r);
        swap(ch, i, l);
      }
    }
  }
  static void swap(char[] ch, int i, int j) {
    char temp=ch[i];
    ch[i]=ch[j];
    ch[j]=temp;
  }
}