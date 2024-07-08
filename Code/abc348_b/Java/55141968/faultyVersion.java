import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in); 
        int n = Integer.parseInt(sc.next());
        
        List<XY> xy = new ArrayList<XY>();
        
        for(int i = 0; i < n; i++){
            xy.add(new XY(Integer.parseInt(sc.next()), Integer.parseInt(sc.next())));
        }
        sc.close();
        
        for(int i = 0; i < n; i++){
            float farthestDist = 0;
            int farthestPoint = 0;
            for(int j = 0; j < n; j++){
                
                float calc = (float)Math.sqrt((Math.pow(xy.get(i).x - xy.get(j).x, 2)) + (Math.pow(xy.get(i).y - xy.get(j).y, 2)));
                if(i == j){

                }
                else if(farthestDist < calc){
                    farthestDist = calc;
                    farthestPoint = j+1;
                }
            }
            //System.out.println(farthestPoint);
        }
    }
}

class XY{
    int x;
    int y;
    
    XY(int x, int y){
        this.x = x;
        this.y = y;
    }
}