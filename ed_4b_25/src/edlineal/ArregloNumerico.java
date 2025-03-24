package edlineal;

public class ArregloNumerico extends Arreglo{

    public ArregloNumerico(int tamanio) {
        super(tamanio);
    }
    //Valida si el numero es de tipo Number
    public boolean validarNumber(Object valor){
        if (valor instanceof Number){
            return true;
        }else{
            return false;
        }
    }
    //Transformamos un objeto a un dato tipo double
    public double transformarDouble(Object valor){
        double nuevoValor= ((Number) valor).doubleValue();
        return nuevoValor;
    }

    @Override
    public Integer poner(Object valor) {
        if (validarNumber(valor)==true){
            double valorNuevo =transformarDouble(valor);
            return super.poner(valorNuevo);
        }else{
            return -1;
        }
    }

    @Override
    public Object quitar(int indice) {
        return super.quitar(indice);
    }

    @Override
    public Object buscar(Object valor) {
        if (validarNumber(valor)==true){
            double valorNuevo =transformarDouble(valor);
            return super.buscar(valor);
        }else{
            return -1;
        }

    }

    @Override
    public boolean modificar(int indice, Object valor) {
        if (validarNumber(valor)==true) {
            double valorNuevo = transformarDouble(valor);
            return super.modificar(indice, valor);
        }else{
            return false;
        }
    }

    public boolean porEscalar(Number escalar){
        //validamos que este vacio
         if(vacia()==true){
             return false;
         }else{
             //transformamos el escalar
             double nuevoEscalar=transformarDouble(escalar);
             //pasamos por el arreglo
             for(int recorredor=0; recorredor<=indiceSuperior;recorredor++){
                 //Hacemos la multiplicacion
                 double nuevoDato=((transformarDouble(datos[recorredor]) * nuevoEscalar));
                 //Insertamos resultad,
                 datos[recorredor]=nuevoDato;
             }
             return true;
         }
    }
    public boolean sumaEscalar(Number escalar){
        //validamos que este vacio
        if(vacia()==true){
            return false;
        }else{
            //transformamos el escalar
            double nuevoEscalar=transformarDouble(escalar);
            //pasamos por el arreglo
            for(int recorredor=0; recorredor<=indiceSuperior;recorredor++){
                //Hacemos la multiplicacion
                double nuevoDato=((transformarDouble(datos[recorredor]) + nuevoEscalar));
                //Insertamos resultad,
                datos[recorredor]=nuevoDato;
            }
            return true;
        }
    }

    public boolean sumar(ArregloNumerico lista2){
        if (this.cantidad()==lista2.cantidad()){
            for(int recorredor=0; recorredor<=this.cantidad()-1;recorredor++){
                double numero1=transformarDouble(datos[recorredor]);
                double numero2=transformarDouble(lista2.datos[recorredor]);
                double resultado=numero1+numero2;
                datos[recorredor]=resultado;
            }
            return true;
        }else{
            return false;
        }
    }
    public boolean multiplicar(ArregloNumerico lista2){
        if (this.cantidad()==lista2.cantidad()){
            for(int recorredor=0; recorredor<=this.cantidad()-1;recorredor++){
                double numero1=transformarDouble(datos[recorredor]);
                double numero2=transformarDouble(lista2.datos[recorredor]);
                double resultado=numero1*numero2;
                datos[recorredor]=resultado;
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean aplicarPotencia(Number escalar){
        if (vacia()){
            return false;
        }else {
            double nuevoEscalar=transformarDouble(escalar);
            for (int recorredor=0; recorredor<=cantidad()-1;recorredor++){

                double datoListaActual=transformarDouble(datos[recorredor]);
                double resultado=1;

                for (int recorredor2=0; recorredor2<=nuevoEscalar-1;recorredor2++){
                    resultado = resultado*datoListaActual;
                }
                datos[recorredor]=resultado;
            }return true;
        }
    }

    public boolean aplicarPotencia(ArregloNumerico listaEscalares){
        if (vacia() || listaEscalares.vacia() || cantidad()>listaEscalares.cantidad() || cantidad()<listaEscalares.cantidad()){
            return false;
        }else{
            for (int recorredor=0; recorredor<=cantidad()-1;recorredor++){
                double datoEscalar=transformarDouble(listaEscalares.datos[recorredor]);
                double datoListaActual=transformarDouble(datos[recorredor]);
                double resultado=1;
                for (int recorredor2=0; recorredor2<=datoEscalar-1;recorredor2++){
                    resultado = resultado*datoListaActual;
                }
                datos[recorredor]=resultado;

            }
            return true;
        }
    }

    private double caluclarPotencia(double valor, int potencia){
        double valorNuevo=transformarDouble(valor);
        if(valorNuevo<=0 || potencia<=0){
            return -1;
        }else{
            double resultado=1;
            for (int recorredor=0; recorredor<potencia;recorredor++){
                resultado=resultado*valorNuevo;
            }return resultado;
        }

    }

    private double calcularRaiz(int valor){
        if (valor<=0){
            return -1;
        }else{
            double resultado=0;

            for(double recorredor=1;recorredor*recorredor<=valor;recorredor++){
                resultado=recorredor;
            }
            return resultado;
        }
    }

    public double productoEscalar(ArregloNumerico lista2){
        //validamos que no esten vacios y que tengan la misma cantidad de datos
        if (vacia() || lista2.vacia() || cantidad()>lista2.cantidad() || cantidad()<lista2.cantidad()) {
            return -1;
        }else{
            double resultadoMultiplicacion;
            double resultadoFinal=0;

            for(int recorredor=0; recorredor<=this.cantidad()-1;recorredor++){
                double numero1=transformarDouble(datos[recorredor]);
                double numero2=transformarDouble(lista2.datos[recorredor]);
                resultadoMultiplicacion=numero1*numero2;
                resultadoFinal=resultadoFinal+resultadoMultiplicacion;
            }return resultadoFinal;
        }
    }

    public double norma(){
        //validamos que no este vacio
        if(vacia()==true){
            return -1;
        }else{
            double resultado=0;
            for(int recorredor=0; recorredor<=cantidad()-1;recorredor++){
                resultado=resultado+caluclarPotencia(transformarDouble(datos[recorredor]),2);
            }
            return calcularRaiz((int) resultado);
        }
    }

    public double normaEuclaniana(ArregloNumerico arreglo2){
        //verificamos que ninguno de los 2 arreglos este vacio
        if(vacia()||arreglo2.vacia()){
            return -1;
        }else{
            double resultado=0;
            for(int recorredor=0; recorredor<=cantidad()-1;recorredor++){
                double dato1=transformarDouble(datos[recorredor]);
                double dato2=transformarDouble(arreglo2.datos[recorredor]);
                resultado=resultado+caluclarPotencia(dato2-dato1,2);
            }
            return calcularRaiz((int) resultado);
        }
    }

    public boolean sumarListaEstatica(Arreglo listas){
        if(vacia() || listas.vacia() || cantidad()<listas.cantidad() || cantidad()>listas.cantidad()){
            return false;
        }else{
            for (int recorredorListas=0; recorredorListas<=listas.cantidad()-1;recorredorListas++){
                ArregloNumerico nuevoArreglo=(ArregloNumerico) listas.datos[recorredorListas];
                if(nuevoArreglo.cantidad()==cantidad()){
                    double resultado=0;
                    for (int recorredorElementos=0;recorredorElementos<=cantidad()-1;recorredorElementos++){
                        resultado=resultado+transformarDouble(nuevoArreglo.datos[recorredorElementos]);
                    }
                    datos[recorredorListas]=resultado+transformarDouble(datos[recorredorListas]);
                }else{return false;}
            }
            return true;
        }
    }

    public double sumarIndices(ArregloNumerico listaIndices){
        //Valida que ninguna de las 2 listas este vacia.
        if(vacia() || listaIndices.vacia()){
            return -1;
        }else{
            double suma = 0;

            // Recorrer listaIndices para obtener las posiciones a sumar
            for (int recorredor = 0; recorredor < listaIndices.cantidad(); recorredor++) {
                int indice = (int) transformarDouble(listaIndices.datos[recorredor]);

                // Validar que el índice esté dentro de los límites del arreglo actual
                if ((indice >= 0) && (indice < cantidad())) {
                    suma = suma+transformarDouble(datos[indice]);
                } else {
                    return -1;
                }
            }

            return suma;
        }


    }

    public boolean sumarEscalares(ArregloNumerico escalares) {
        // Validamos que los arreglos no estén vacíos
        if (vacia() || escalares.vacia()) {
            return false;
        }

        // Recorremos todos los escalares
        for (int recorredor = 0; recorredor < escalares.cantidad(); recorredor++) {
            double escalar = transformarDouble(escalares.datos[recorredor]);

            // Sumamos este escalar a todos los elementos del arreglo actual
            for (int recorredorEscalar = 0; recorredorEscalar < cantidad(); recorredorEscalar++) {
                datos[recorredorEscalar] = transformarDouble(datos[recorredorEscalar]) + escalar;
            }
        }

        return true;
    }

    @Override
    public Arreglo subLista(ArregloNumerico listaIndices) {
        return super.subLista(listaIndices);
    }

    @Override
    public boolean recibirBuffer(double[] buffer2) {
        return super.recibirBuffer(buffer2);
    }

    @Override
    public void preEnfasis() {
        super.preEnfasis();
    }
}
