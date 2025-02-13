package edlineal;

import tools.Enumerador.TipoOrden;
import tools.comunesBase.ManipuladorObjetos;

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

    @Override
    public boolean validarLista(Object lista) {
        return super.validarLista(lista);
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


    public Arreglo convertirAreglo(ListaDatos lista){
        Arreglo arregloNuevo=(Arreglo) lista;
        return arregloNuevo;
    }


    public boolean agregarLista(ListaDatos lista2){
        Arreglo arregloNuevo=convertirAreglo(lista2);
        if (validarLista(arregloNuevo)==true){
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
        return super.modificar(indice, valor);
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
}
