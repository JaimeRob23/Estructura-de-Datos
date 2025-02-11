package registros.ventas;

public class Vendedor {
    protected String nombre;
    protected String RFC;
    protected int numeroVendedor;
    protected String fechaNacimiento;

    public Vendedor(String nombre, String RFC, String fechaNacimiento, int numeroVendedor){
        this.nombre=nombre;
        this.numeroVendedor=numeroVendedor;
        this.RFC=RFC;
        this.fechaNacimiento=fechaNacimiento;
    }
    @Override
    public String toString(){
        return numeroVendedor+"";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public int getNumeroVendedor() {
        return numeroVendedor;
    }

    public void setNumeroVendedor(int numeroVendedor) {
        this.numeroVendedor = numeroVendedor;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
