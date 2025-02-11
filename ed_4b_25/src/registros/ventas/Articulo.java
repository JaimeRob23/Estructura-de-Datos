package registros.ventas;

public class Articulo {
    protected String descripcion;
    protected String clave;
    protected double precioU;

    public Articulo(String descripcion, String clave, double precioU){
        this.clave=clave;
        this.descripcion=descripcion;
        this.precioU=precioU;
    }
    @Override
    public String toString(){
        return clave;
    }
    //atributo unico, no se le puede agregar nada que no lo relacione especificamente



















    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public double getPrecioU() {
        return precioU;
    }

    public void setPrecioU(double precioU) {
        this.precioU = precioU;
    }
}
