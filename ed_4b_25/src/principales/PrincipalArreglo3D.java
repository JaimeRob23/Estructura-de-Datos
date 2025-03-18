package principales;

import ednolineal.Arreglo3D;
import entradasalida.Salida;

public class PrincipalArreglo3D {
    public static void main(String[] args) {
        Arreglo3D cubo = new Arreglo3D(5,4,3,0);

        cubo.imprimirXColumnas();

        cubo.cambiarCelda(1,2,0,90);
        cubo.imprimirXColumnas();

        //Object valorCelda=cubo.obtenerCelda(1,2,0);
        //Salida.salidaPorDefecto("Valor de la celda : "+valorCelda);
    }
}
