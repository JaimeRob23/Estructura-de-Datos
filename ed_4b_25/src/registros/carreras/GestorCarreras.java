package registros.carreras;

import edlineal.Arreglo;
import ednolineal.Arreglo3D;
import entradasalida.Salida;

public class GestorCarreras {
    protected Arreglo3D kilometrosRecorridos; //Guarda solo los kilometros
    protected Arreglo corredores; //Me guarda la info de los corredores
    protected Arreglo eventos; //Me guarda la infor de las carreras
    protected Arreglo anios; //Me guarda la info de los años

    //Creo un constructor que me indique cuántos hay de cada cosa
    public GestorCarreras(int numCorredores, int numEventos,
                          int numAnios){
        corredores=new Arreglo(numCorredores);
        eventos=new Arreglo(numEventos);
        anios=new Arreglo(numAnios);
        kilometrosRecorridos=new Arreglo3D(numCorredores,
                numEventos, numAnios, 0.0);
    }

    //Método para agregar corredores
    public boolean agregarCorredor(int noCorredorAsignado,
                                   String nombre, int edad,
                                   char sexo){
        //Para crear un corredor, me pasan sus datos y luego hago
        // un new, despupés lo meto al arreglo de corredores
        Corredor nuevoCorredor=new Corredor(noCorredorAsignado,
                nombre, edad, sexo);
        //antes de ponerlo en le arreglo corredores,
        //hay que ver que no se duplique ese número de corredor
        int busquedaCorredor=(int)corredores.buscar(nuevoCorredor);

        if(busquedaCorredor>=0){ //es decir, existe, no lo agregamos
            return false;
        }
        //cualquier cosa abajo se hace si no existe

        int retorno=corredores.poner(nuevoCorredor);
        if(retorno>=0 ){
            return true;
        }else{ //me regresó -1, error
            return false;
        }
    }

    //Método para agregar eventos
    public boolean agregarEvento(String nombre, String lugar,
                                 int anioFundacion){

        //Recibo los datos del evento
        //Checo que no exista ese evento
        int busquedaEvento=(int)eventos.buscar(nombre);

        //Sino existe lo agregamos al arreglo de eventos
        if(busquedaEvento>=0){ //ya existe ese vento
            return false; //no puedo agregar un nuevo evento
        }
        //Solo se crea el objeto si se va agregar
        //Creo un evento nuevo con esos datos
        Evento nuevoEvento=new Evento(nombre, lugar, anioFundacion);

        //Agregarlo al arreglo de eventos
        int retorno=eventos.poner(nuevoEvento);
        if(retorno>=0){ //si pudo agregarlo
            return true;
        }else{ //hubo error
            return false;
        }
    }

    //Método para agregar añios
    public boolean agregarAnio(int anio){
        //Recibo el dato del año a agregar,
        //Primero checo que ese año no exista
        int busquedaAnio=(int)anios.buscar(anio);
        if(busquedaAnio<0){ //no existe, -1
            //Entonces lo agregamos
            //En caso que no exista, lo puedo agregar
            int retorno= anios.poner(anio);
            if (retorno>=0){ //si pudo
                return true;
            }else{ //hay error, -1
                return false;
            }
        }else{ //ya existe
            return false;
        }
    }

    //Busca los índices de una celda y me los regresa
    //en un arreglo, 0 (Corredor), 1 (Evento), 2 (Anio)
    private Arreglo buscarIndicesKms(int noCorredor, String nombreE,
                                     int anio){
        int indiceEvento=(int)eventos.buscar(nombreE);
        int indiceCorredor=(int)corredores.buscar(noCorredor);
        int indiceAnio=(int)anios.buscar(anio);

        if(indiceEvento>=0 && indiceCorredor>=0 && indiceAnio>=0){
            //son válidos
            //Creo un arreglo con esos índices y lo retorno
            Arreglo indices=new Arreglo(3);
            indices.poner(indiceCorredor); //0
            indices.poner(indiceEvento); //1
            indices.poner(indiceAnio); //2
            return indices;
        }else{ //uno no se encontró
            return null;
        }

    }

    //Método que agrega kilómetros
    public boolean agregarKilometros(double km, int noCorredor,
                                     String nombreEvento, int anio){
        //Me dan los datos de dónde se va a guardar
        //el kilometraje recorrido,
        //Necesito encontrar los índices de esos datos
            Arreglo indicesKms=buscarIndicesKms(noCorredor,
                nombreEvento, anio);
        if(indicesKms!=null){
            //Para con esos índices poder en el cubo, guardarlos kms
            int indiceEvento=(int)indicesKms.obtener(1);
            int indiceCorredor=(int)indicesKms.obtener(0);
            int indiceAnio=(int)indicesKms.obtener(2);
            return kilometrosRecorridos.cambiarCelda(indiceCorredor, indiceEvento, indiceAnio, km);
        }else{ //el asreglo de índices es nulo, no ppodemos hacer nada
            return false;
        }
    }

    public void mostrarDatos(){
        Salida.salidaPorDefecto("Eventos: \n");
        eventos.imprimirDes();
        Salida.salidaPorDefecto("\n");
        Salida.salidaPorDefecto("Corredores: \n");
        corredores.imprimirDes();
        Salida.salidaPorDefecto("\n");
        Salida.salidaPorDefecto("Anios: \n");
        anios.imprimirDes();
        Salida.salidaPorDefecto("\n");
        Salida.salidaPorDefecto("Kms: \n");
        kilometrosRecorridos.imprimirXColumnas();
        Salida.salidaPorDefecto("\n");
    }

    public Double kilometrosXCorredor(int noCorredor, Arreglo carrerasParticipante, Arreglo aniosParticipantes){
        //se tiene que calcular varias carreras
        //un solo corredor
        //tengo que ir sumando todos los kilometros de cada carrera de un corredor en ciertps anios
        Double kmCorredor=0.0;
        for(int cadaCarrera=0; cadaCarrera<carrerasParticipante.cantidad();cadaCarrera++){//del arreglo de carreras participantes
            String carreraTemporal=(String) carrerasParticipante.obtener(cadaCarrera);
            Double kmCarreraEvento=kilometroXCorredorXCarrera(noCorredor,carreraTemporal,aniosParticipantes);
            //acumulamos los km de cada carrera para ese corredor
            if(kmCarreraEvento==null){//con uno que sea null no se realiza nada
                return null;
            }else{
                kmCorredor = kmCorredor+kmCarreraEvento;
            }
        }
        return kmCorredor;
    }

    public Double kilometroXCorredorXCarrera(int noCorredor, String carreraParticipante, Arreglo aniosParticipantes){
        //obtener los km de un corredor de una carrera
        //pero de todos los anios
        Double kmanio=0.0;
        for(int cadaAnio=0;cadaAnio<aniosParticipantes.cantidad();cadaAnio++){
            //obtener una celda del cubo con kms de fila,col,prof.
            //Necesito los 3 datos indirectos de numero de corredor, nom del evento y el anio.
            int anioTemporal=(int) aniosParticipantes.obtener(cadaAnio);
            //obtener los indices(datos directos del cubo)
            //de los tres datos indirectos(noCorredor,carreraParticipante,anioTemporal)
            Arreglo indicesDirectos=buscarIndicesKms(noCorredor,carreraParticipante,anioTemporal);
            //ahora puedo obtener de una celda los km
            Double kmsCelda=(Double) kilometrosRecorridos.obtenerCelda((int)indicesDirectos.obtener(0),(int)indicesDirectos.obtener(1),(int)indicesDirectos.obtener(2));
            if(kmsCelda==null){
                return null;
            }else{
                //acumulo kms de cada anio
                kmanio=kmanio+kmsCelda;
            }

        }
        return kmanio;
    }
}
