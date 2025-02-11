package edlineal;

import tools.comunesBase.ManipuladorObjetos;

public class ArregloOrdenado extends Arreglo{

    public ArregloOrdenado(int tamanio) {
        super(tamanio);
    }

    @Override
    public Object buscar(Object valor){
        int recorredor = 0;
        while((recorredor<=indiceSuperior)&&(ManipuladorObjetos.compararObjetos(valor,datos[recorredor])>0)){
            recorredor=recorredor+1;
        }
        //primero, no se encontro
        if(recorredor>indiceSuperior|| ManipuladorObjetos.compararObjetos(valor,datos[recorredor])<0){
            return (recorredor+1)*(-1);
        }else{//igual, si se encontro
            return recorredor+1;
        }
    }

    public Integer poner(Object valor){
        if(lleno()==false){
            int posicionEncontrado=(int) buscar(valor);
            if(posicionEncontrado<0){
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
}
