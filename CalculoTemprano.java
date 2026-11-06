// Clase que calcula Inicio Temprano y Fin Temprano
public class CalculoTemprano {

    public static void calcular(String[][] matriz, int cantidad) {
        boolean terminado;
        do {
            terminado = true;
            // Recorremos cada actividad
            for (int i = 1; i <= cantidad; i++) {
                // Si ya está calculado saltamos
                if (!matriz[i][7].equals("---")) continue;

                String predecesoresStr = matriz[i][6];

                // Si no tiene predecesores empieza en 0
                if (predecesoresStr.equalsIgnoreCase("Ninguno") || predecesoresStr.isEmpty()) {
                    int duracion = Integer.parseInt(matriz[i][5]);
                    matriz[i][7] = "0";
                    matriz[i][8] = String.valueOf(0 + duracion);
                    continue;
                }

                // Se separan los predecesores por coma
                String[] predecesores = predecesoresStr.split(",");
                int maxEF = -1;
                boolean todosListos = true;

                // Buscamos el mayor Fin Temprano de los predecesores
                for (String nom : predecesores) {
                    nom = nom.trim();
                    int idx = buscarIndice(matriz, cantidad, nom);
                    if (idx == -1 || matriz[idx][8].equals("---")) {
                        todosListos = false;
                        break;
                    }
                    int efPre = Integer.parseInt(matriz[idx][8]);
                    if (efPre > maxEF) maxEF = efPre;
                }

                // Si todos predecesores están listos calculamos
                if (todosListos) {
                    int duracion = Integer.parseInt(matriz[i][5]);
                    matriz[i][7] = String.valueOf(maxEF);
                    matriz[i][8] = String.valueOf(maxEF + duracion);
                } else {
                    terminado = false;
                }
            }
        } while (!terminado);
    }
    
    // Método auxiliar para buscar el índice de una actividad por su nombre
    private static int buscarIndice(String[][] matriz, int cantidad, String nombre) {
        for (int i = 1; i <= cantidad; i++) {
            if (matriz[i][1].equalsIgnoreCase(nombre)) return i;
        }
        return -1;
    }
}