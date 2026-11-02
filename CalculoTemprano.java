public class CalculoTemprano {
    
    public static void calcular(String[][] matriz, int cantidad) {

        //Recorremos todas las actividades
        for (int i = 1; i <= cantidad; i++) {
            
            int inicio = 0;
            String predecesores = matriz[i][6];

            //Comprobamos si la actividad tiene predecesores
            if (!predecesores.equalsIgnoreCase("Ninguno")
                    && !predecesores.equals("---")
                    && !predecesores.isEmpty()) {
                //Separa y recorre los predecesores de la actividad
                String[] anteriores = predecesores.split(",");

                for (int k = 0; k < anteriores.length; k++) {
                    
                    String nombre = anteriores[k].trim();

                    //Busca cada predecesor en la matriz
                    for (int j = 1; j <= cantidad; j++) {

                        if (matriz[j][1].equalsIgnoreCase(nombre)){

                            //Obtiene el mayor tiempo de finalización de los predecesores
                            int finAnterior = Integer.parseInt(matriz[j][8]);

                            if (finAnterior > inicio) {
                                inicio = finAnterior;
                            }

                            break;
                        }
                    }
                }
            }

            //Calcula los tiempos de inicio y finalización de la actividad
            int duracion = Integer.parseInt(matriz[i][5]);

            int fin = inicio + duracion;

            //Guarda los resultados en la matriz
            matriz[i][7] = String.valueOf(inicio);
            matriz[i][8] = String.valueOf(fin);
        }
        
    }
}
