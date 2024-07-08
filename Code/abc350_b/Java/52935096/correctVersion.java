import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
       int a = sc.nextInt();
       int b = sc.nextInt();
       //bは操作回数＝numbersの長さ
       int numbers[];
      numbers=new int [b]; 
		//ArrrayListは勝手にクラスを指定できる＆要素数が可変
		for(int x=0;x<b;x++){
		  int number = sc.nextInt();
      numbers[x]=number;}
	
   
    int c=0;
    int counts=0;
    //奇数回治療したやつ　歯がないやつ
     for(c=1;c<(a+1);c++){int count=0;
       for(int d=0;d<b;d++){
         if(c==numbers[d]){
         count++;
        
       } }
       if(count%2==1){counts++;
         
       }
       
       
     
      
     }
    System.out.println(a-counts);}
    
     
}

		
		
	