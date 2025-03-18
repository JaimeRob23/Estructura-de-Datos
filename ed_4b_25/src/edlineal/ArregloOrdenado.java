package edlineal;

import tools.Enumerador.TipoOrden;
import tools.comunesBase.ManipuladorObjetos;

import java.util.Random;

public class ArregloOrdenado extends Arreglo{


    private TipoOrden orden;

    public ArregloOrdenado(int tamanio, TipoOrden orden){
        super(tamanio);
        this.orden=orden;
    }

    @Override
    public Object buscar(Object valor){
        int recorredor = 0;
        if(orden==TipoOrden.INC){
            while((recorredor<=indiceSuperior)&&(ManipuladorObjetos.compararObjetos(valor,datos[recorredor])>0)){
                recorredor=recorredor+1;
            }
            //primero, no se encontro la posicion
            if(recorredor>indiceSuperior|| ManipuladorObjetos.compararObjetos(valor,datos[recorredor])<0){
                return (recorredor+1)*(-1);
            }else{//igual, si se encontro la posicion
                return recorredor+1;
            }
        }else{
            while((recorredor<=indiceSuperior)&&(ManipuladorObjetos.compararObjetos(valor,datos[recorredor])<0)){
                recorredor=recorredor+1;
            }

            //primero, no se encontro encontro la posicion
            if(recorredor>indiceSuperior|| ManipuladorObjetos.compararObjetos(valor,datos[recorredor])>0){
                return (recorredor+1)*(-1);
            }else{//igual, si se encontro encontro la posicion
                return recorredor+1;
            }
        }


    }

    public Object quitar(Object valor){
        int posicion=(int) buscar(valor);
        posicion=posicion-1;
        Object respaldo;
         if (validarPosicion(posicion)==true){
             respaldo=datos[posicion];
             for(int modificacion=posicion; (modificacion<=indiceSuperior-1);modificacion++){
                 datos[modificacion]=datos[modificacion+1];
             }
             indiceSuperior=indiceSuperior-1;
             return respaldo;
         }else{
             return null;
         }
    }
    public ListaDatos arregloDesordenado() {
        Arreglo arregloDes = new Arreglo(capacidad);

        // Copiar los elementos al nuevo arreglo
        for (int recorredor = 0; recorredor <= indiceSuperior; recorredor++) {
            arregloDes.poner(this.datos[recorredor]);
        }
        Random random = new Random();
        for (int recorredor = 0; recorredor < indiceSuperior; recorredor++) {
            int indiceRandom = recorredor + random.nextInt(this.indiceSuperior - recorredor); // Índice aleatorio entre recorredor e indiceSuperior
            // Intercambiar datos[recorredor] con datos[indiceRandom]
            Object temp = arregloDes.datos[recorredor];
            arregloDes.datos[recorredor] = arregloDes.datos[indiceRandom];
            arregloDes.datos[indiceRandom] = temp;
        }

        return arregloDes;
    }


    public boolean validarPosicion(int posicion){
        if (posicion<0){
            return false;
        }else{
            return true;
        }
    }

    public Integer poner(Object valor){
        if(lleno()==false){
            int posicionEncontrado=(int) buscar(valor);
            if(validarPosicion(posicionEncontrado)==false){
                posicionEncontrado=posicionEncontrado * (-1);
                posicionEncontrado=posicionEncontrado - 1;
                for (int posMod=(indiceSuperior+1); posMod>=(posicionEncontrado+1);posMod--){
                    datos[posMod]=datos[posMod-1];

                }
                datos[posicionEncontrado]=valor;
                indiceSuperior=indiceSuperior+1;
                return posicionEncontrado;
            }else{
                return -1;

            }
        }else{
            return -1;
        }



    }


    public ArregloOrdenado convertirAreglo(ListaDatos lista){
        ArregloOrdenado arregloNuevo=(ArregloOrdenado) lista;
        return arregloNuevo;
    }


    public boolean validarListaOrdenada(Object lista) {
        if (lista instanceof ArregloOrdenado){
            return true;
        }else{
            return false;
        }
    }

    public boolean agregarLista(ListaDatos lista2){
        ArregloOrdenado arregloNuevo=convertirAreglo(lista2);
        if ((validarListaOrdenada(arregloNuevo)==true)&& (lleno()== false)){
            for(int recorredor=0; recorredor<=arregloNuevo.indiceSuperior; recorredor++){
                poner(arregloNuevo.datos[recorredor]);
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean modificar(int indice, Object valor) {
        if (validarPosicion(indice)==true){
            for(int modificacion=indice; (modificacion<=indiceSuperior-1);modificacion++){
                datos[modificacion]=datos[modificacion+1];
            }
            indiceSuperior=indiceSuperior-1;
            poner(valor);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void invertir() {
        super.invertir();
        if(orden.getOrden()==1){
            //el orden es incremental y se cambiara por decremental
            orden.setOrden(2);
        }else{
            //el orden es decremental y se cambiara por incremental
            orden.setOrden(1);
        }
    }

    public boolean esSubLista(ListaDatos lista2){
        //primero validamos
        if(validarListaOrdenada(lista2)==true) {
            ArregloOrdenado arreglo = convertirAreglo(lista2);
            for (int recorredor = 0; recorredor <= arreglo.indiceSuperior; recorredor++) {
                int resultadoBusqueda = (int) buscar(arreglo.datos[recorredor]);
                if (resultadoBusqueda < 0) {
                    return false;
                }

            }
            return true;
        }else {
            return false;
        }
    }

    public boolean retenerLista(ListaDatos lista2){
        if(validarLista(lista2)==true){
            ArregloOrdenado listaNueva= convertirAreglo(lista2);
            if (listaNueva.cantidad()>capacidad){
                return false;
            }else{
                vaciar();
                for(int recorredor=0; recorredor<=listaNueva.cantidad()-1; recorredor++){
                    poner(listaNueva.datos[recorredor]);
                }
            }
            return true;
        }return false;
    }

    public boolean substituir(ListaDatos lista2){
            if(validarLista(lista2)==true){
                ArregloOrdenado listaNueva=convertirAreglo(lista2);
                if(listaNueva.cantidad()>capacidad){
                    return false;
                }else{
                    //Verificar que se respete el orden
                    if(listaNueva.orden.getOrden()==orden.getOrden()){
                        vaciar();
                        for(int recorredor=0;recorredor<=listaNueva.cantidad()-1;recorredor++){
                            poner(listaNueva.datos[recorredor]);
                        }
                        return true;
                    }else{
                        return false;
                    }
                }


            }return false;
    }

    @Override
    public boolean validaPosicion(int indice) {
        return super.validaPosicion(indice);
    }




    public boolean poner(int indice, Object info) {
        // Validar si el índice está dentro del rango permitido y que el arreglo no esté lleno
        if ((validaPosicion(indice)==false) || lleno()) {
            return false;
        }
        if (orden == TipoOrden.INC) {
            // Verificar que el valor a insertar sea mayor o igual que el anterior y menor o igual que el siguiente
            if (( ManipuladorObjetos.compararObjetos(datos[indice - 1], info) > 0) ||
                    (ManipuladorObjetos.compararObjetos(info, datos[indice]) > 0)) {
                return false;
            }
        } else if (orden == TipoOrden.DEC) {
            // Verificar que el valor a insertar sea menor o igual que el anterior y mayor o igual que el siguiente
            if ((ManipuladorObjetos.compararObjetos(datos[indice - 1], info) < 0) ||
                    (ManipuladorObjetos.compararObjetos(info, datos[indice]) < 0)) {
                return false;
            }
        }
        // Mover los elementos a la derecha para hacer espacio
        for (int recorredor = indiceSuperior; recorredor >= indice; recorredor--) {
            datos[recorredor + 1] = datos[recorredor];
        }
        // Insertar el nuevo valor en la posición
        datos[indice] = info;
        indiceSuperior++;
        return true;
    }
}
