public class CalculoTemprano {
    
    public static void calcular(String[][] matriz, int cantidad) {

        for (int i = 1; i <= cantidad; i++) {
            
            int inicio = 0;
            String predecesores = matriz[i][6];

            if (!predecesores.equalsIgnoreCase("Ninguno")
                    && !predecesores.equals("---")
                    && !predecesores.isEmpty()) {
                String[] anteriores = predecesores.split(",");

                for (int k = 0; k < anteriores.length; k++) {
                    
                    String nombre = anteriores[k].trim();

                    for (int j = 1; j <= cantidad; j++) {

                        if (matriz[j][1].equalsIgnoreCase(nombre)){

                            int finAnterior = Integer.parseInt(matriz[j][8]);

                            if (finAnterior > inicio) {
                                inicio = finAnterior;
                            }

                            break;
                        }
                    }
                }
            }

            int duracion = Integer.parseInt(matriz[i][5]);

            int fin = inicio + duracion;

            matriz[i][7] = String.valueOf(inicio);
            matriz[i][8] = String.valueOf(fin);
        }
        
    }
}
