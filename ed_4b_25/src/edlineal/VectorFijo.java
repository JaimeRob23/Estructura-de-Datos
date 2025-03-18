package edlineal;
//Esta interfaz maneja los metodos particulares y estatica

public interface VectorFijo extends ListaDatos{
    public boolean lleno();
    public int capacidad();
    public int cantidad();

    //checar rangos validos

    public Object obtener(int indice);
    public boolean modificar(int indice, Object valor);
    public boolean modificarLista(Arreglo indicesBusqueda, Arreglo valoresNuevos);
}
