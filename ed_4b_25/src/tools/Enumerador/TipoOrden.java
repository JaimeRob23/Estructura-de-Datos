package tools.Enumerador;
// Esta clase es para saber el tipo de ordenamiento de los datos en los ArreglosOrdenados
public enum TipoOrden {

    INC(1),  // Incremental (ascendente)
    DEC(2);  // Decremental (descendente)
    int orden;

    // Constructor para asignar valores
    private TipoOrden(int valor) {
        this.orden = valor;
    }

    // Un get por si se llegara a necesitar saber el orden de la tabla
    public int getOrden() {
        return orden;
    }
     public void setOrden(int orden){
        this.orden=orden;
     }

}
