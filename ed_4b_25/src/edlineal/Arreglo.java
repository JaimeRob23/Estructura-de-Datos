package edlineal;

import entradasalida.Salida;

public class Arreglo  implements VectorFijo {
    protected int capacidad;
    protected Object datos[];
    protected int indiceSuperior;
    protected Arreglo articulosVenta;

    public Arreglo(int tamanio) {
        this.capacidad = tamanio;
        this.datos = new Object[this.capacidad];
        this.indiceSuperior = -1;
    }

    public boolean vacia() {
        if (indiceSuperior == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean lleno() {
        if (indiceSuperior == (capacidad - 1)) {
            return true;
        } else {
            return false;
        }

    }

    public Integer poner(Object valor) {
        if (lleno() == false) {
            indiceSuperior = indiceSuperior + 1;
            datos[indiceSuperior] = valor;
            return indiceSuperior;
        } else {
            return -1;
        }

    }

    @Override
    public Object buscar(Object valor) {
        int recorredor = 0;
        while ((recorredor <= indiceSuperior) && (valor.toString().equalsIgnoreCase(datos[recorredor].toString()) == false)) {
            recorredor = recorredor + 1;
            //el to string es para convertir en cadenas
            //el equals ignore es para que compare una cadena con la otra sin mayusculas y eso
        }
        if (recorredor > indiceSuperior) {
            return -1;
        } else {
            return recorredor;
        }
    }

    @Override
    public void imprimir() {
        int posicion;
        for (posicion = 0; posicion <= this.indiceSuperior; posicion++) {
            Salida.salidaPorDefecto(datos[posicion] + "\n");
        }

    }

    public void imprimirDes() {
        int posicion;
        for (posicion = indiceSuperior; posicion >= 0; posicion--) {
            Salida.salidaPorDefecto(datos[posicion] + "\n");
        }
    }

    public Object quitar() {
        if (vacia() == false) {
            Object datoExtraido = datos[indiceSuperior];
            indiceSuperior = indiceSuperior - 1;
            return datoExtraido;
        } else {
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
        if (vacia() == false) {
            return datos[indiceSuperior];
        } else {
            return null;
        }
    }

    @Override
    public void invertir() {
        for (int recorredor=0; recorredor<=indiceSuperior/2;recorredor++){
            //se divide entre 2 ya que solo iteraremos hasta la mitad de la lista
            Object temporal=datos[recorredor];
            //se hace una variable temporal
            datos[recorredor]=datos[indiceSuperior  - recorredor];
            //el dato en la posicion 0 se guarda en el ultimo dato que es el indice superior
            datos[indiceSuperior  - recorredor]= temporal;
            //el ultimo dato osea el indice superior se guarda en la posicion 0
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
    @Override
    public boolean validaPosicion(int indice){
        if(indice>=0 && indice<=indiceSuperior){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean substituir(ListaDatos lista2) {
        return false;
    }

    @Override
    public Object verFinal() {
        if(vacia()){
            return null;
        }else{
            return datos[indiceSuperior];
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
            datos[indice]=valor;
            return true;
        }

        return false;
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
    @Override
    public boolean poner(int indice, Object info){
        if((validaPosicion(indice)==true)&&(indiceSuperior>=indice)){
            datos[indice]=info;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean modificarLista1(ListaDatos lista2, ListaDatos lista2Nuevos) {
        if((validarLista(lista2)==true)&&(validarLista(lista2Nuevos)==true)){
            Arreglo arregLista2=convertirAreglo(lista2);
            Arreglo arregloLista2Nuevos=convertirAreglo(lista2Nuevos);
            if (arregLista2.cantidad() == arregloLista2Nuevos.cantidad()) {
                int sustituir = 0;


                for (int recorredor = 0; recorredor < arregLista2.cantidad(); recorredor++) {

                    int datoB = (int) buscar(arregLista2.datos[recorredor]);

                    if (datoB > 0) {
                        datos[datoB] = arregloLista2Nuevos.obtener(recorredor);
                        sustituir++;
                    }
                }
                if (sustituir>0){
                    return true;
                }else {
                    return false;
                }


            }return false;



        }return false;

    }
    public Arreglo convertirAreglo(ListaDatos lista){
        Arreglo arregloNuevo=(Arreglo) lista;
        return arregloNuevo;
    }

    public Arreglo subLista(ArregloNumerico listaIndices) {
        if (vacia() || listaIndices.vacia()) {
            return null;
        }

        Arreglo arregloNuevo = new Arreglo(listaIndices.cantidad());

        for (int recorredorIndices = 0; recorredorIndices < listaIndices.cantidad(); recorredorIndices++) {
            double indiceRecuperado = (double) listaIndices.datos[recorredorIndices];
            int indice = (int) indiceRecuperado;

            if (indice >= 0 && indice < cantidad()) {
                Object valorRecuperado = datos[indice];
                arregloNuevo.poner(valorRecuperado);
            }
        }

        return arregloNuevo;
    }

    public boolean recibirBuffer(double[] buffer2) {
        if (capacidad < buffer2.length) {//verificamos dimensiones
            return false;
        } else {
            // Si hay espacio suficiente, copiamos los datos de buffer2 en datos
            for (int recorredorBuffer = 0; recorredorBuffer < buffer2.length; recorredorBuffer++) {
                datos[recorredorBuffer] =buffer2[recorredorBuffer];  // Asignación directa de 'double' a 'double'
            }
            // Actualizamos indiceSuperior
            this.indiceSuperior = buffer2.length - 1;
            return true;  // Devolvemos true si salio bien
        }
    }

    public Object[] leerArreglo(){
        if(vacia()==true){//Se valida que no este vacia
            return null;
        }else{
            Object[] copiaParaBuffer=new Object[cantidad()];//Se crea la nueva copia
            for(int recorredorDatos=0;recorredorDatos<cantidad();recorredorDatos++){
                copiaParaBuffer[recorredorDatos]=datos[recorredorDatos];//Se pasan los datos
            }return copiaParaBuffer;//Se retorna la lista
        }
    }

    public void preEnfasis(){
        double valorAlpha=0.97;//Se genera el valor aplha
        for (int recorredor = 1; recorredor < cantidad(); recorredor++) {
            double ayudenmePorFavor=(double) datos[recorredor-1];
            datos[recorredor] = ((double) datos[recorredor] - (valorAlpha*ayudenmePorFavor ));//Se hacen las operaciones
            //Se guarda el nueva dato en nuestra lista
        }



    }




}
