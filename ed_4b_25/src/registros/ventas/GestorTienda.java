package registros.ventas;

import edlineal.Arreglo;

public class GestorTienda {
    protected String nombre;
    protected Arreglo notasVenta;
    protected  Arreglo vendedores;

    public GestorTienda(String nombre, int cantNotas, int cantVendedores){
        this.nombre=nombre;
        notasVenta=new Arreglo(cantNotas);
        vendedores=new Arreglo(cantVendedores);
    }

    public boolean agregarVendedor(String nombre, String RFC,String fechaNacimiento, int numeroVendedor){
        Vendedor vendedorTemp=new Vendedor(nombre,RFC,fechaNacimiento,numeroVendedor);
        vendedores.poner(vendedorTemp);

        int retorno=vendedores.poner(vendedorTemp);
        if (retorno<0){
            return false;
        }else{
            return true;
        }

    }
    public boolean agregarNota(Cliente cliente, String fecha,int cantArtVend){
        Vendedor vendedorTemp=(Vendedor)vendedores.obtener(0);

        if (vendedorTemp==null){
            return false;
        }
        int cantidadNotasExistentes=notasVenta.cantidad();
        int folioTemp=0;
        NotaVenta notaTemp=null;
        if (cantidadNotasExistentes==0){
            folioTemp=1;//no hay notas
        }else{
            //obtener nota arriba
            notaTemp=(NotaVenta) notasVenta.obtener(cantidadNotasExistentes-1);
            folioTemp=notaTemp.getFolio();
        }
        NotaVenta notaNueva=new NotaVenta(cantArtVend,cliente,vendedorTemp,fecha,folioTemp);
        //agregar nota nueva
        int retorno=notasVenta.poner(notaNueva);
        if (retorno<0){
            //error
            return false;
        }else {
            return true;
        }
    }


    public boolean agregarArticuloNota(int folio, Articulo artComprado, int cant){
        //Encontrar nota que tenga el folio proporcionado
        //En esa nota agregar el articulo indicado
        int posiconBusqueda=(int)notasVenta.buscar(folio);

        if (posiconBusqueda<0){
            return false;
        }else{
            NotaVenta notaTemp=(NotaVenta) notasVenta.obtener(posiconBusqueda);
            return notaTemp.agregarArticulo(artComprado,cant);
        }
    }

    public void imprimirNota(int folio){
        int posicionNota=(int) notasVenta.buscar(folio);

        if (posicionNota>=0){
            //obtener la
            NotaVenta notaTemp=(NotaVenta) notasVenta.obtener(posicionNota);
            //imprimirla
            notaTemp.imprimirNota();

        }
    }
}
