import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int personas = sc.nextInt();
        int distanciaLimite = sc.nextInt();
        List<Persona> stats = new ArrayList<>();

        for(int i = 0; i < personas; i++){

            int ejeX = sc.nextInt();
            int ejeY = sc.nextInt();
            stats.add(new Persona(ejeX, ejeY, false));
        }
        stats.get(0).infectado = true; //Primera persona infectada

        comprobarInfeccion(stats, stats.get(0), distanciaLimite);

        for(Persona persona : stats){
            System.out.println(persona.infectado ? "Yes" : "No");
        }
    }

    static void comprobarInfeccion(List<Persona> personas, Persona personaInfecciosa, int distancia){

        for(Persona p : personas){
            if(!p.infectado && personaInfecciosa.dentroDeDistancia(p, distancia)){
                //Se infecta a la persona actual
                p.infectado = true;
                //Comprobamos a quien puede infectar la persona
                comprobarInfeccion(personas, p, distancia);
            }
        }
    }

    static class Persona{

        int ejeX;
        int ejeY;
        boolean infectado;

        public Persona(int ejeX, int ejeY, boolean infectado){
            this.ejeX = ejeX;
            this.ejeY = ejeY;
            this.infectado = infectado;
        }

        boolean dentroDeDistancia(Persona persona, int distancia){

            double distanciaEuclidea = Math.sqrt(
                    Math.pow(this.ejeX - persona.ejeX, 2) +
                            Math.pow(this.ejeY - persona.ejeY, 2));

            return distancia >= distanciaEuclidea;
        }

    }
}
