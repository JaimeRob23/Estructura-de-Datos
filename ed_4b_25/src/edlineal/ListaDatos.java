package edlineal;
//esta interfaz maneja memoria estatica y dimnamica
public interface ListaDatos {
    public boolean vacia();
    public Integer poner(Object valor);
    public Object buscar(Object valor);
    public void imprimir();
    public Object quitar();
    public Object quitar(int valor);
    public boolean validarLista(Object lista2);
    public boolean esIgual(Arreglo lista);
    public boolean modificar(Object valorAntiguo, Object valorNuevo, int numVeces);
    public Arreglo buscarValores(Object valor);
    public void vaciar();
    public Arreglo clonar();
    public boolean poner(int indice, Object info);
    public Object verUltimo();
    public void invertir();
    boolean validaPosicion(int indice);
    public boolean substituir(ListaDatos lista2);
    //public boolean modificarLista(ListaDatos lista2, ListaDatos lista2Nuevos);
}
