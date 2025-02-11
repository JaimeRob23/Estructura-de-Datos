package tools.comunesBase;

public class ManipuladorObjetos {

    //si objeto 1 es mayor a objeto2
    //si objeto 1 es menor a objeto 2
    //lo que es numeo se va a comparar como numero, si no se comparan como objetos
    public static int compararObjetos(Object objeto1, Object objeto2){
        if(objeto1 instanceof Number && objeto2 instanceof Number){
            //los 2 son numeros
            Double numero1=Double.parseDouble(objeto1.toString());
            Double numero2=Double.parseDouble(objeto2.toString());

            if (numero1>numero2){
                return +1;

            }else if(numero1<numero2){
                return -1;
            }else{
                return 0;
            }

        }else{//por lo menos uno no es numero
            //el metodo compare to regresa positibo si o1 es mayor que o2, si es menoj regresa negativo, si son iguales regresa 0
            return objeto1.toString().compareToIgnoreCase(objeto2.toString());
        }
    }
}
