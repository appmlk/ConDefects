

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String S = scanner.next();
    scanner.close();
    
    HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();
    for (int i = 0; i < S.length(); i++) {
      char temp = S.charAt(i);
      if(frequency.containsKey(temp)) {
        frequency.put(temp, frequency.get(temp) + 1);
      } else {
        frequency.put(temp, 1);
      }
    }
    
//    System.out.println(getKeyWithHighestValue(frequency));
    
  }
  
  public static Character getKeyWithHighestValue(HashMap<Character, Integer> map) {
    Character keyWithHighestValue = null;
    int highestValue = 0;
    
    for (Map.Entry<Character, Integer> e : map.entrySet()) {
      if (keyWithHighestValue == null) {
        keyWithHighestValue = e.getKey();
        highestValue = e.getValue();
      } else if (e.getValue() > highestValue) {
        keyWithHighestValue = e.getKey();
        highestValue = e.getValue();
      } else if (e.getValue().equals(highestValue) && e.getKey() < keyWithHighestValue) {
        keyWithHighestValue = e.getKey();
        highestValue = e.getValue();
      }
    }
    return keyWithHighestValue;
  }
  
  
}
