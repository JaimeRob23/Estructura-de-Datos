package principales;
import edlineal.PilaFija;
public class PrincipalPila {
    public static void main(String[] args) {
        PilaFija pila1=new PilaFija(6);
        pila1.poner("A");
        pila1.poner("S");
        pila1.poner("T");
        pila1.poner("M");
        pila1.poner("R");

        pila1.imprimir();
    }
}
