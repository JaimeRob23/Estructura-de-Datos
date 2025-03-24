package archivoAudio;

import java.io.*;

import archivoAudio.wavfile.*;
import edlineal.Arreglo;
import edlineal.ArregloNumerico;
import entradasalida.Salida;
import entradasalida.archivos.ArchivoTextoD;

public class AudioFileRecord {
    private long numFrames;  //numero de tramas, número de muestras totales del archivo por canal
    private long sampleRate; //numero de muestras por segundo a la que se discretiza la señal
    private int numChannels; //número de canales
    private double[] buffer; //audio original
    private double[] copiaBuffer; //audio modificado
    private String archivo;   //nombre de archivo dado por el usuario
    private WavFile wavFileR; //apuntador de archivo leido
    private WavFile wavFileW;  //apuntador de archivo a escribir
    private String nomArchivo; //nombre archivo (uno.wav)
    private String nomRuta;    //ruta donde esta guardado el archivo (/home)
    private int validBits;//bits usados para la discretización
    public ArregloNumerico buffer2;


    public AudioFileRecord(String archivo) {
        this.archivo = archivo;
        // Abre el archivo wav y asigna parámetros a las variables
        try {
            File file = new File(archivo);
            wavFileR = WavFile.openWavFile(file);
            nomArchivo = file.getName();
            nomRuta = file.getParent();
        } catch (Exception e) {

        }
        numChannels = wavFileR.getNumChannels();
        numFrames = wavFileR.getNumFrames();
        sampleRate = wavFileR.getSampleRate();
        validBits=wavFileR.getValidBits();
    }

    public void leerAudio() {
        try {

            // Muestra datos del archivo
            wavFileR.display();

            // Crea buffer de origen y de temporal
            buffer = new double[(int) numFrames * numChannels];
            copiaBuffer = new double[buffer.length];


            //tramas leidas
            int framesRead;

            // Lee tramas totales
            framesRead = wavFileR.readFrames(buffer, (int) numFrames);

            // Recorrer todas las tramas del archivo y guardarlas en el arreglo.
            for (int s = 0; s < framesRead * numChannels; s++) {
                copiaBuffer[s] = buffer[s];
            }


            buffer2=new ArregloNumerico(copiaBuffer.length);
            buffer2.recibirBuffer(copiaBuffer);


            // Cierra archivo
            wavFileR.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    public void EscribirAudio() {
        try {
            Object[] arregloTemporal = buffer2.leerArreglo();

            for(int recorredorArregTemporal = 0; recorredorArregTemporal < arregloTemporal.length; recorredorArregTemporal++){
                copiaBuffer[recorredorArregTemporal]=(double) arregloTemporal[recorredorArregTemporal];
            }

            //Crear el apuntador al archivo para guardar datos del temporal
            File file = new File(nomRuta + "/2_" + nomArchivo);

            // Creamos un nuevo archivo de audio con los mismos datos que el original
            wavFileW = WavFile.newWavFile(file, numChannels, numFrames, validBits, sampleRate);

            // Escribimos los frames o muestras totales del temporal
            wavFileW.writeFrames(copiaBuffer, (int) numFrames);

            // Cerramos el archivo
            wavFileW.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void subirVolumen(int intensidad){
        if (buffer2.vacia()) {//Validamos que no este vacia
            Salida.salidaPorDefecto( "El buffer está vacío.");
        }else{
            double factor = 1.0 + (intensidad / 100.0); // Convertir intensidad en un porcentaje para que quede (1.5,1.6,etc.)
            buffer2.porEscalar(factor);//reutilizamos un metodo ateriormente agregado el cual multiplica un valor por cada dato en la lista
        }
    }

    public void bajarVolumen(int intensidad){
        if (buffer2.vacia()) {//Validamos que no este vacia
            Salida.salidaPorDefecto("El buffer está vacío.");
        }else{
            double factor = 1.0 - (intensidad / 100.0); // Convertir intensidad en un porcentaje el cual se ira restando al original
            buffer2.porEscalar(factor);//reutilizamos un metodo ateriormente agregado el cual multiplica un valor por cada dato en la lista
        }
    }

    public void acelerar(int velocidad){
        if(buffer2.vacia()==true){
            Salida.salidaPorDefecto("El buffer está vacío.");
        }else{
            int tamanioActualizado = buffer2.cantidad()/ velocidad; //calculamos el nuevo tamanio
            ArregloNumerico nuevoArregloAcelerado=new ArregloNumerico(tamanioActualizado);//creamos otro arreglo con el nuevo tamanio

            for(int recorredorNuevoArreglo=0; recorredorNuevoArreglo<nuevoArregloAcelerado.capacidad();recorredorNuevoArreglo++){//este for recorrera la nueva lista
                double suma=0;
                double promedio;

                for (int recorredorBuffer2 = 0; recorredorBuffer2 < velocidad; recorredorBuffer2++) {//este for solo itera las veces que indique la velocidad

                    int indiceOriginal=recorredorNuevoArreglo* velocidad +recorredorBuffer2;//Esta formula lo que hace es que calcula cual es el siguiente numero de cada iteracion
                    if(indiceOriginal<buffer2.cantidad()){
                        suma = suma+buffer2.transformarDouble(buffer2.obtener(indiceOriginal));//Aqui se realiza la suma
                    }
                }
                promedio=suma/ velocidad;
                nuevoArregloAcelerado.poner(promedio);
            }
            buffer2=nuevoArregloAcelerado;
            this.numFrames=nuevoArregloAcelerado.cantidad()/numChannels;
        }

    }

    public void escribirGrafica(){
        ArchivoTextoD archivoTxt =new ArchivoTextoD();
        Arreglo arreglo = buffer2;
        archivoTxt.escribir(arreglo,"archivoTxt");

    }

    public void eliminarSilencios() {
        if(buffer2.vacia()==true){
        }else{
            // Crear un nuevo arreglo para almacenar las muestras sin silencios
            double umbralSilencioPositivo = 0.0005;  // Ajusta este valor
            double umbralSilencioNegativo = -0.0005;  // Ajusta este valor
            ArregloNumerico nuevoArreglo = new ArregloNumerico(buffer2.capacidad());

            for (int recorredorBuffer = 0; recorredorBuffer < buffer2.cantidad(); recorredorBuffer++) {
                double muestra = (double)(buffer2.obtener(recorredorBuffer));

                // Si la muestra no es silencio, la agregamos al nuevo arreglo
                if (muestra > umbralSilencioPositivo || muestra<umbralSilencioNegativo) {
                    nuevoArreglo.poner(muestra);
                }


            }
            // Redefinir buffer2 con el nuevo arreglo filtrado
            buffer2 = nuevoArreglo;
            this.numFrames = nuevoArreglo.cantidad() / numChannels;
        }

    }

    public void invertirX(){
        if (buffer2.vacia()==true){
        }else{
            buffer2.invertir();
        }
    }
    public void invertirY(){
        if (buffer2.vacia()==true){
        }else{
            buffer2.porEscalar(-1);
        }
    }





}
