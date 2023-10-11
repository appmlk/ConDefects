import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String cadena = in.nextLine();
        StringBuilder retorno = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            retorno.append(cadena.charAt(i)).append(cadena.charAt(i));
        }
        System.out.println(retorno);
    }
}