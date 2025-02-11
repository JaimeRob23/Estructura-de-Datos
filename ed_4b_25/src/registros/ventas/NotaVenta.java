package registros.ventas;

import edlineal.Arreglo;
import entradasalida.Salida;

public class NotaVenta {
    protected int folio;
    protected String fecha;
    public Vendedor vendedorVenta;
    protected Cliente clienteVenta;
    protected Arreglo articulosVenta;
    protected Arreglo cantidadArticulos;
    protected double total;

    public NotaVenta(int cantidadArticulosVenta,Cliente cliente, Vendedor vendedor,String fecha,int folio){
        clienteVenta=cliente;
        vendedorVenta=vendedor;
        this.fecha=fecha;
        articulosVenta=new Arreglo(cantidadArticulosVenta);
        cantidadArticulos= new Arreglo(cantidadArticulosVenta);
        this.folio=folio;
        total=0;
    }

    public boolean agregarArticulo(Articulo articuloVendido,int cantidad){
        int resultadoInsercionA=articulosVenta.poner(articuloVendido);
        int resultadoInsercionC=cantidadArticulos.poner(cantidad);

        if (resultadoInsercionC==-1 || resultadoInsercionA==-1){
            //hubo error
            return false;
        }else{
            return true;
        }
    }

    public double calcularTotal(){
        //recorrer los arreglos paralelos
        //y obtener los articulos y sus costos
        //para multiplicarlo por la cantidad vendida
        double total=0.0;

        for (int posicionArreglo=0; posicionArreglo<articulosVenta.cantidad();posicionArreglo++){
            Articulo articuloTemp=(Articulo)articulosVenta.obtener(posicionArreglo);
            double precioUnitario=articuloTemp.getPrecioU();
            int cantiad=(int)cantidadArticulos.obtener(posicionArreglo);
            double subTotal=precioUnitario*cantiad;
            total=total+subTotal;

        }
        return total;
    }

    public void imprimirNota(){
        Salida.salidaPorDefecto("**********Nota de Venta**********");
        Salida.salidaPorDefecto("Folio"+folio);
        Salida.salidaPorDefecto("\t\t\t Fecha: "+fecha+"\n");
        Salida.salidaPorDefecto("Cajero "+vendedorVenta.getNombre()+"\n");
        Salida.salidaPorDefecto("Cliente "+clienteVenta.getNombre()+"\n");

        Salida.salidaPorDefecto("No. articulo \t\t Clave \t\t Descripcion \t\t Cantidad \t\t Precio \t\t SubTotal\n");

        //imprimir articulos

        for(int cadaArticulo=0; cadaArticulo<articulosVenta.cantidad(); cadaArticulo++){
            Salida.salidaPorDefecto(""+(cadaArticulo+1)+"\t\t\t\t");

            Articulo articuloTemp=(Articulo) articulosVenta.obtener(cadaArticulo);
            Salida.salidaPorDefecto(articuloTemp.getClave()+"\t\t\t\t");
            Salida.salidaPorDefecto(articuloTemp.getDescripcion()+"\t\t\t\t");

            int cantidadTemp=(int)cantidadArticulos.obtener(cadaArticulo);
            Salida.salidaPorDefecto(cantidadTemp+"\t\t\t\t");

            Salida.salidaPorDefecto(articuloTemp.getPrecioU()+"\t\t\t\t");
            double subTotal=articuloTemp.getPrecioU()*cantidadTemp;
            Salida.salidaPorDefecto(subTotal+"\t\t\n");
        }
        total=calcularTotal();
        Salida.salidaPorDefecto("\t\t\t\t\t\t\t\t Total: "+total);
    }

    public String toString() {
        return ""+folio;
    }

    public int getFolio() {
        return folio;
    }

    public Arreglo getArticulosVenta() {
        return articulosVenta;
    }

    public Arreglo getCantidadArticulos() {
        return cantidadArticulos;
    }

}
