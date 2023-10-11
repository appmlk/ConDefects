

import java.util.*;

public class Main{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();


        String s = "";
        if(r == c || r == 16 -c){

            if(c% 2 == 1) s = "black";
            else{ s = "white";}
        }
        else if(r > c){

            if( r < 16 - c){
                s = c%2 == 1? "black" : "white";
            }else{

                s = r % 2 == 1 ? "black" : "white";
            } 
        }
        else if(r < c){


            if( c < 16 -r) {

                s = r % 2 == 1? "black" : "white"; 
            }else { 
                s = r % 2 == 1? "black" : "white"; 
                }
        }


            System.out.println(s);


    }
}
