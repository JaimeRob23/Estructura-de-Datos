package edlineal;

public interface LoteDatos {
    public boolean lleno();
    public boolean vacio();
    public boolean poner(Object valor);
    public Object quitar();
    public void imprimir();
    public Object verTope();//el tope es e final de la ED
}
