package principales;

import edlineal.Arreglo;
import edlineal.ArregloOrdenado;
import edlineal.ListaDatos;
import entradasalida.Salida;
import tools.Enumerador.TipoOrden;

public class PrincipalAOrdenado {
    public static void main(String[] args) {

        ArregloOrdenado lista=new ArregloOrdenado(8,TipoOrden.INC);
        lista.poner("1");
        lista.poner("2");
        lista.poner("3");
        lista.poner("6");
        lista.poner("7");
        ArregloOrdenado lista2=new ArregloOrdenado(3,TipoOrden.INC);
        lista2.poner("2");
        lista2.poner("6");
        lista2.poner("8");





    }
}
