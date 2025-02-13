package ednolineal;

import entradasalida.Salida;

public class Arreglo3D {
    protected int ancho;
    protected int alto;
    protected int profundidad;
    protected Object datos[][][];
    //primero[] filas (alto), el segundo[] es columnas(ancho) y el tercero[] es lo profundo

    public Arreglo3D(int ancho, int alto, int profundidad) {
        this.ancho = ancho;
        this.alto = alto;
        this.profundidad = profundidad;
    }

    public Arreglo3D(int ancho, int alto, int profundidad, Object valor){
        this(ancho, alto, profundidad);
        // invocar al metodo rellenar usando valor....
        rellenar(valor);

    }

    public void rellenar(Object valor){
        //cuando los manipules separalos por arreglos independientes(unidimensional)
        //ciclo para lo alto filas
        for (int cadaFila=0; cadaFila<alto;cadaFila++){
            for (int cadaCol=0; cadaCol<ancho;cadaCol++){
                for (int cadaProf=0; cadaProf<profundidad; cadaProf++){
                    //por cada celda [] [] []
                    datos[cadaFila][cadaCol][cadaProf]=valor;
                }
            }
        }
    }

    public void imprimirXColumnas(){
        //dividir el cubo en rebanadas

        for(int cadaRebanada=0; cadaRebanada<ancho; cadaRebanada++){
            //ahora voy a imprimir una rebanada y lo que reporesenta
            //primero agarro los renglones de la matriz que es una rebanada
            for (int cadaFila=0; cadaFila<alto; cadaFila++){
                for (int cadaCol = 0; cadaCol <profundidad; cadaCol++){
                    Salida.salidaPorDefecto(datos[cadaFila][cadaRebanada][cadaCol]+" ");
                }
                //cuando se impriman todas las columnas de una fila saltamos de linea
                Salida.salidaPorDefecto("\n");

            }
            //cuando se impriman todos los renglones de una rebanada, saltamos de linea
            Salida.salidaPorDefecto("\n");

        }
    }
}
