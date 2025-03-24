package principales;

import archivoAudio.AudioFileRecord;

public class PrincipalAudio {
    public static void main(String[] args) {
        AudioFileRecord audio=new AudioFileRecord("C:\\Users\\marco\\Desktop\\repo\\Estructura-de-Datos\\ed_4b_25\\src\\archivoAudio\\audiosGrabados\\prueba.wav");
        audio.leerAudio();
        audio.eliminarSilencios();
        audio.EscribirAudio();
        audio.escribirGrafica();
    }
}
