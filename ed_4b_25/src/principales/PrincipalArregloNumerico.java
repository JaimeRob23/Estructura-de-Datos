package principales;

import edlineal.Arreglo;
import edlineal.ArregloNumerico;
import entradasalida.Salida;

public class PrincipalArregloNumerico {
    public static void main(String[] args) {
        ArregloNumerico lista1 = new ArregloNumerico(3);
        lista1.poner(8);
        lista1.poner(2);
        lista1.poner(12);
        ArregloNumerico lista2 = new ArregloNumerico(3);
        lista2.poner(1);
        lista2.poner(2);
        lista2.poner(1);
        Arreglo lista3 =lista1.subLista(lista2);
        lista3.imprimir();














//        Arreglo arreglo=new Arreglo(3);
//        ArregloNumerico lista1DentroArreglo= new ArregloNumerico(3);
//        lista1DentroArreglo.poner(4);
//        lista1DentroArreglo.poner(5);
//        lista1DentroArreglo.poner(6);
//        ArregloNumerico lista2DentroArreglo = new ArregloNumerico(3);
//        lista2DentroArreglo.poner(2);
//        lista2DentroArreglo.poner(3);
//        lista2DentroArreglo.poner(4);
//        ArregloNumerico lista3DentroArreglo = new ArregloNumerico(3);
//        lista3DentroArreglo.poner(8);
//        lista3DentroArreglo.poner(9);
//        lista3DentroArreglo.poner(10);
//        arreglo.poner(lista1DentroArreglo);
//        arreglo.poner(lista2DentroArreglo);
//        arreglo.poner(lista3DentroArreglo);
//        lista1.sumarListaEstatica(arreglo);
//        lista1.imprimirDes();
//        ArregloNumerico lista2= new ArregloNumerico(3);
//        lista2.poner(2);
//        lista2.poner(3);
//        lista2.poner(5);
//        Salida.salidaPorDefecto("Resulttado de la norma euclaniana: "+lista1.normaEuclaniana(lista2)+"\n");



    }
}
