package edlineal;

import entradasalida.Salida;

public class Arreglo  implements VectorFijo{
    protected int capacidad;
    protected Object datos[];
    protected int indiceSuperior;
    protected Arreglo articulosVenta;

    public Arreglo(int tamanio){
        this.capacidad=tamanio;
        this.datos = new Object[this.capacidad];
        this.indiceSuperior = -1;
    }

    public boolean vacia(){
        if (indiceSuperior == -1){
            return true;
        }else{
            return false;
        }
    }

    public boolean lleno(){
        if(indiceSuperior == (capacidad -1)){
            return true;
        }else{
            return false;
        }

    }

    public Integer poner(Object valor) {
        if(lleno() == false){
            indiceSuperior= indiceSuperior + 1;
            datos[indiceSuperior]=valor;
            return indiceSuperior;
        }else{
            return -1;
        }

    }

    @Override
    public Object buscar(Object valor){
        int recorredor = 0;
        while((recorredor<=indiceSuperior)&&(valor.toString().equalsIgnoreCase(datos[recorredor].toString())==false)){
            recorredor=recorredor+1;
            //el to string es para convertir en cadenas
            //el equals ignore es para que compare una cadena con la otra sin mayusculas y eso
        }
        if(recorredor>indiceSuperior){
            return -1;
        }else{
            return recorredor;
        }
    }

    @Override
    public void imprimir(){
        int posicion;
        for(posicion=0; posicion<=this.indiceSuperior; posicion++){
            Salida.salidaPorDefecto(datos[posicion]+"\n");
        }

    }
    public void imprimirDes(){
        int posicion;
        for(posicion= indiceSuperior; posicion>=0; posicion--){
            Salida.salidaPorDefecto(datos[posicion]+"\n");
        }
    }
    public Object quitar(){
        if(vacia()==false){
           Object datoExtraido=datos[indiceSuperior];
           indiceSuperior= indiceSuperior - 1;
           return datoExtraido;
        }else{
            return null;
        }
    }
    public Arreglo clonar() {
        Arreglo clon = new Arreglo(capacidad);

        for (int recorredor = 0; recorredor <= indiceSuperior; recorredor++) {
            clon.datos[recorredor] = datos[recorredor];
        }

        clon.indiceSuperior = indiceSuperior;

        return clon;
    }

    @Override
    public Object verUltimo() {
        if (vacia()==false){
            return datos[indiceSuperior];
        }else{
            return null;
        }
    }

    public void vaciar() {
        indiceSuperior = -1;
    }

    public Object quitar(int indice){
        Object respaldo;
        if(validaPosicion(indice)==true){
            respaldo=datos[indice];
            for(int modificacion=indice; (modificacion<=indiceSuperior-1);modificacion++){
                datos[modificacion]=datos[modificacion+1];
            }
            indiceSuperior=indiceSuperior-1;
            return respaldo;
        }else{
            return null;
        }

    }
    //Me dice el tamaño del arreglo
    public int capacidad(){
        return capacidad;
    }
    //Me dice cuantos datos hay en el arreglo
    public int cantidad() {
        return indiceSuperior+1;
    }

    //checar rangos validos
    private boolean validaPosicion(int indice){
        if(indice>=0 && indice<=indiceSuperior){
            return true;
        }else{
            return false;
        }
    }
    //Este metodo obtiene un objeto del arreglo indicado por la posicion del indice
    public Object obtener(int indice) {
        if(validaPosicion(indice)==true){
            return datos[indice];
        }else{
            return null;
        }
    }

    private double calcularTotal(){
        for(int posicioonArreglo=0;posicioonArreglo<=articulosVenta.cantidad();posicioonArreglo++){
        }return 0.0;
    }
    //se validara si es una lista y no otra cosa
    public boolean validarLista(Object lista){
        if(lista instanceof Arreglo){
            return true;
        }else{
            return false;
        }
    }

    //se validara si 2 listas son iguales
    public boolean esIgual(Arreglo lista){
        //primero se valida la lista
        validarLista(lista);
        //asigno los valores a comparar
        Object comparador1;
        Object comparador2;
        //este if es si por logica uno es de un tamaño diferente que el otro, es falso
        if (indiceSuperior==lista.indiceSuperior){
            for (int recorredor=0; recorredor<=indiceSuperior; recorredor++){
                comparador1=datos[recorredor];
                comparador2=lista.datos[recorredor];
                if (comparador1.toString().equals(comparador2.toString())==false){
                    return false;
                }
            }return true;
        }else{
            return false;
        }
    }

    public boolean modificar(Object valorAntiguo, Object valorNuevo, int numVeces) {
        if(numVeces<=0){
            return false;
        }
        int valorEncontrado=0;
        for(int recorredor=0; recorredor<=indiceSuperior; recorredor++){

            if(datos[recorredor].toString().equals(valorAntiguo)){
                datos[recorredor]=valorNuevo;
                valorEncontrado++;
            }
            if (valorEncontrado==numVeces){
                return true;
            }

        }
        if (valorEncontrado>0){
            return true;
        }else{
            return false;
        }

    }



    public boolean modificar(int indice, Object valor){
        if (validaPosicion(indice)==true){
            return false;
        }
        datos[indice]=valor;

        return true;
    }

    public boolean modificarLista(Arreglo indicesBusqueda, Arreglo valoresNuevos) {
        int variableIndiceBusqueda;
        Object variableValorNuevo;
        if (indicesBusqueda.indiceSuperior==valoresNuevos.indiceSuperior){
            for (int recorredor=0; recorredor<=indicesBusqueda.indiceSuperior; recorredor++){
                variableIndiceBusqueda=Integer.parseInt(indicesBusqueda.datos[recorredor].toString());
                variableValorNuevo=valoresNuevos.datos[recorredor];

                if (validaPosicion(variableIndiceBusqueda)==true){
                    datos[variableIndiceBusqueda]=variableValorNuevo;
                }
            }return true;
        }else{
            return false;
        }

    }

    public Arreglo buscarValores(Object valor) {
        Object valorLista;
        Arreglo posiciones;
        posiciones=new Arreglo(10);
        for (int recorrido=0; recorrido<=this.indiceSuperior;recorrido++){
            valorLista=datos[recorrido];
            if (valorLista.toString().equals(valor.toString())){
                posiciones.poner(recorrido);
            }
        }
        return posiciones;
    }





}
