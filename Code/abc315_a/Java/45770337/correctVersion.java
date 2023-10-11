import java.util.Scanner;
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String text = scanner.next();
    char[] work = new char[text.length()];
    int j = 0;
    
    for(int i = 0; i < text.length(); i++){
      if((text.charAt(i) != 'a') && (text.charAt(i) != 'e') && (text.charAt(i) != 'i')
        && (text.charAt(i) != 'o') && (text.charAt(i) != 'u')) {
        work[j] = text.charAt(i);
        j = j + 1;
      }
    }
    
    String newText = "";
    for(int i = 0; i < work.length; i++) {
        newText = newText + work[i];
    }
    
    if (newText.equals("")) {
      System.out.println(text);
    }
    else {
      System.out.println(newText.trim());
    }
  }
}