package registros.ventas;

public class Cliente {
    protected String RFC;
    protected String nombre;
    protected char sexo;

    public Cliente(String RFC, String nombre, char sexo){
        this.RFC=RFC;
        this.nombre=nombre;
        this.sexo=sexo;
    }

    @Override
    public String toString(){
        return RFC;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
