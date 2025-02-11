package principales;

import registros.ventas.Articulo;
import registros.ventas.Cliente;
import registros.ventas.GestorTienda;

public class PrincipalVenta {
    public static void main(String args[]){
        GestorTienda tienda=new GestorTienda("Travis Store",5,2);

        tienda.agregarVendedor("Juanito", "BEA364785GF56","12/12/2000",1);
        tienda.agregarVendedor("Cruz", "ZRFA2936520932","12/12/2005",1);

        Cliente cliente1=new Cliente("CKMR1234658932MZ","Adrian",'H');
        tienda.agregarNota(cliente1, "12/2/2025", 10);
        Articulo articulo1=new Articulo("Agua","001", 10);
        Articulo articulo2=new Articulo("Jugo","T07", 13);
        Articulo articulo3=new Articulo("Papas","072", 18);
        tienda.agregarArticuloNota(1,articulo1, 2);
        tienda.agregarArticuloNota(1,articulo2, 1);
        tienda.agregarArticuloNota(1,articulo3, 4);

        tienda.imprimirNota(1);
    }
}
