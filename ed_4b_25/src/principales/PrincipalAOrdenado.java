package principales;

import edlineal.Arreglo;
import edlineal.ArregloOrdenado;
import entradasalida.Salida;
import tools.Enumerador.TipoOrden;

public class PrincipalAOrdenado {
    public static void main(String[] args) {

        ArregloOrdenado lista=new ArregloOrdenado(8, TipoOrden.INC);
        lista.poner("1");
        lista.poner("2");
        lista.poner("4");
        lista.poner("6");
        lista.poner("9");


        lista.invertir();
        lista.imprimirDes();

        // Salida.salidaPorDefecto("Quitando 3: "+ lista.quitar("3")+("\n"));

        // PRUEBA COMMIT
        /*
        ArregloOrdenado lista=new ArregloOrdenado(6);
        lista.poner("M");
        lista.poner("O");
        lista.poner("S");
        lista.poner("V");
        lista.poner("B");
        lista.imprimir();
        Salida.salidaPorDefecto("\n");

        Salida.salidaPorDefecto("Buscando S: "+ lista.buscar("S")+("\n"));
  */
    }
}
