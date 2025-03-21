package edlineal;

public class PilaFija implements LoteDatos{
    protected Arreglo pila;

    public PilaFija(int tamanio){
        pila = new Arreglo(tamanio); //La pila usa un arreglo para funcionar
    }
    @Override
    public boolean lleno() {
        return pila.lleno();
    }

    @Override
    public boolean vacio() {
        return pila.vacia();
    }

    @Override
    public boolean poner(Object valor) {
        int retorno= pila.poner(valor);
        if(retorno>=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Object quitar() {
        return pila.quitar();
    }

    @Override
    public void imprimir() {
        pila.imprimirDes();
    }

    @Override
    public Object verTope() {
        return pila.verFinal();
    }
}
