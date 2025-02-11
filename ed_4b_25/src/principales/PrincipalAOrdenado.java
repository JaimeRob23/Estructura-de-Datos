package principales;

import edlineal.ArregloOrdenado;
import entradasalida.Salida;

public class PrincipalAOrdenado {
    public static void main(String[] args) {

        // PRUEBA COMMIT
        ArregloOrdenado lista=new ArregloOrdenado(6);
        lista.poner("M");
        lista.poner("O");
        lista.poner("S");
        lista.poner("V");
        lista.poner("B");
        lista.imprimirDes();
        Salida.salidaPorDefecto("\n");
        Salida.salidaPorDefecto("Buscando Z: "+ lista.buscar("Z")+("\n"));
        Salida.salidaPorDefecto("Buscando S: "+ lista.buscar("S")+("\n"));
    }
}
